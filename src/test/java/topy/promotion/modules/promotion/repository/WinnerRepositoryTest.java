package topy.promotion.modules.promotion.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import topy.promotion.infra.QuerydslConfig;
import topy.promotion.modules.promotion.domain.Winner;

@Import(QuerydslConfig.class)
@ActiveProfiles("test")
@DataJpaTest
class WinnerRepositoryTest {

    @Autowired
    private WinnerRepository sut;

    @Test
    @DisplayName("promotion의 title로 해당 promotion의 우승자를 조회한다.")
    void should_FindAllWinner_by_PromotionTitle() {
        // Arrange
        String promotionTitle = "Test Promotion";
        String winnerName = "LSH";

        Winner winner = Winner.builder()
            .winnerName(winnerName)
            .participatedPromotionTitle(promotionTitle)
            .build();
        sut.save(winner);

        // Act
        List<Winner> actual = sut.findAllByParticipatedPromotionTitle(promotionTitle);

        // Assert
        assertThat(actual).extracting(Winner::getWinnerName).containsOnly(winnerName);
    }
}