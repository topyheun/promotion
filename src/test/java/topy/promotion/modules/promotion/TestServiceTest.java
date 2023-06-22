package topy.promotion.modules.promotion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.repository.RewardRepository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

class TestServiceTest {

    @Mock
    private RewardRepository rewardRepository;

    @InjectMocks
    private TestService testService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Reward reward = Reward.builder()
                .name("아이폰")
                .quantity(100)
                .rank(Rank.FIRST)
                .build();
        rewardRepository.save(reward);
    }

    @Test
    void 경품차감_분산락_동시성_100명_테스트() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    // 분산락 적용 메서드 호출 (락의 key는 쿠폰의 name으로 설정)
                    testService.rewardDecrease("핸드폰", "아이폰");
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        Reward reward = rewardRepository.findByName("아이폰")
                        .orElseThrow(IllegalArgumentException::new);

        assertEquals(0, reward.getQuantity());
        System.out.println("잔여 경품 갯수 = " + reward.getQuantity());
    }
}
