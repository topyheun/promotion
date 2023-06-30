package topy.promotion.modules.promotion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.repository.RewardRepository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestServiceTest {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private TestService testService;

    int numberOfThreads = 100;

    @BeforeEach
    void setUp() {
        Reward reward = Reward.builder()
                .name("아이폰")
                .quantity(numberOfThreads)
                .rank(Rank.FIRST)
                .build();
        rewardRepository.save(reward);
    }

    @Test
    void 경품차감_분산락_동시성_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    testService.rewardDecrease("핸드폰", "아이폰");
                    System.out.println("매서드 몇 번 실행함?");
                } finally {
                    latch.countDown();
                    System.out.println("finally는 몇 번 실행하나?");
                }
            });
        }
        System.out.println("for문 끝나나?");
        executorService.shutdown();
        latch.await();
9
        System.out.println("여기까지 오나??");
        Reward reward = rewardRepository.findByName("아이폰")
                .orElseThrow(() -> new RuntimeException("에러!!!"));

        int quantity = reward.getQuantity();
        assertEquals(0, quantity);
    }
}
