package topy.promotion.modules.promotion.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import topy.promotion.infra.QuerydslConfig;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.Reward;

@Import(QuerydslConfig.class)
@ActiveProfiles("test")
@DataJpaTest
class RewardRepositoryTest {

    @Autowired
    private RewardRepository sut;

    @Autowired
    private PromotionRepository promotionRepository;

    @Test
    @DisplayName("등록하려는 이름의 reward가 이미 존재한다면 true를 리턴한다.")
    void should_ReturnTrue_when_RewardExists() {
        // Arrange
        String promotionTitle = "Test Promotion";
        String rewardName = "Test Reward";

        Promotion promotion = Promotion.builder()
            .title(promotionTitle)
            .build();
        promotionRepository.save(promotion);

        Reward reward = Reward.builder()
            .name(rewardName)
            .promotion(promotion)
            .build();
        sut.save(reward);

        // Act
        boolean actual = sut.existsByNameAndPromotion_Title(rewardName, promotionTitle);

        // Assert
        assertThat(actual).isTrue();
    }
}