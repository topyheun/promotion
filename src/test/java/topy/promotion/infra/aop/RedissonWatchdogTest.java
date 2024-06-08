package topy.promotion.infra.aop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedissonWatchdogTest {

    @Test
    @DisplayName("Redis 클라이언트 종료 후 lockWatchdogTimeout이 동작하여 락이 해제되는지 확인한다.")
    public void should_UnLock_When_WatchdogTimeoutExpireAfterShutdown() throws InterruptedException {
        // Arrange
        String lockKey = "Test Lock";

        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        config.setLockWatchdogTimeout(10 * 1000); // lockWatchdogTimeout 10초로 설정

        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock(lockKey);

        // Act
        lock.lock(); // leaseTime 없이 락을 획득
        assertThat(lock.isLocked()).isTrue(); // 락이 걸려있는지 확인

        redissonClient.shutdown(); // Redis Client 종료
        Thread.sleep(11 * 1000); // lockWatchdogTimeout 시간 동안 기다림 (11초 대기)

        RedissonClient redissonClientForTest = Redisson.create(config);
        RLock lockForTest = redissonClientForTest.getLock(lockKey);

        // Assert
        assertThat(lockForTest.tryLock()).isTrue(); // lockWatchdogTimeout이 동작하여 락이 해제되었는지 확인
    }
}