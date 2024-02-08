package topy.promotion.modules.promotion.service;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.repository.PromotionRepository;
import topy.promotion.modules.promotion.repository.RewardRepository;

@SpringBootTest
class RewardSearchServiceTest {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @MockBean
    private RewardCreateRankService rewardCreateRankService;

    @Autowired
    private RewardSearchService sut;

    @Test
    @DisplayName("50개의 Thread에 의해 Reward가 수령될 때, 분산락을 통해 올바르게 수령되는지 확인한다.")
    public void should_DistributeReward_when_ReceivedRewardFiftyThreads() throws Exception {
        // Arrange
        String promotionTitle = "제비뽑기";
        Rank mockRank = Rank.WIN;

        Promotion promotion = new Promotion(promotionTitle, LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        promotionRepository.save(promotion);

        Reward reward = new Reward("세탁기", 100, mockRank, promotion);
        rewardRepository.save(reward);

        when(rewardCreateRankService.createRank()).thenReturn(mockRank);

        final int threadCount = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);

        // Act
        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            executor.submit(() -> {
                try {
                    startLatch.await();
                    return sut.getWinnerReward(promotionTitle);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
                return null;
            });
        }
        startLatch.countDown();
        endLatch.await();
        executor.shutdown();

        // Assert
        Reward actual = rewardRepository.findById(1L).orElseThrow();
        Assertions.assertEquals(0, actual.getQuantity());
    }
}