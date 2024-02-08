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
    @DisplayName("leaseTime없이 Lock 획득 후 종료되었을 때, WatchdogTimeout이 잘 작동되는지 확인한다.")
    public void should_UnLock_When_WatchdogTimeoutExpireAfterShutdown() throws InterruptedException {
        // Arrange
        String lockKey = "Test Lock";

        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        config.setLockWatchdogTimeout(10 * 1000); // WatchdogTimeout을 10초로 설정

        RedissonClient lockingRedissonClient = Redisson.create(config);
        RLock lock = lockingRedissonClient.getLock(lockKey);

        // Act
        lock.lock(); // leaseTime 없이 락을 획득

        RedissonClient testingRedissonClient = Redisson.create(config);
        RLock lockForTest = testingRedissonClient.getLock(lockKey);

        assertThat(lockForTest.tryLock()).isFalse(); // 첫 번째 클라이언트가 락을 갖고 있기 때문에 락을 획득할 수 없음

        lockingRedissonClient.shutdown(); // 첫 번째 클라이언트 종료
        Thread.sleep(10 * 1000); // WatchdogTimeout 시간 동안 기다림

        // Assert
        assertThat(lockForTest.tryLock()).isTrue(); // 락이 해제되었는지 확인
    }
}