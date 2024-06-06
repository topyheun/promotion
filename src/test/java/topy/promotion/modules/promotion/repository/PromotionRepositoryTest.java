package topy.promotion.modules.promotion.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import topy.promotion.infra.config.QuerydslConfig;
import topy.promotion.modules.promotion.domain.Promotion;

@Import(QuerydslConfig.class)
@ActiveProfiles("test")
@DataJpaTest
class PromotionRepositoryTest {

    @Autowired
    private PromotionRepository sut;

    @Test
    @DisplayName("등록하려는 이름의 promotion이 이미 존재한다면 true를 리턴한다.")
    void should_ReturnTrue_when_PromotionExists() {
        // Arrange
        String promotionTitle = "Test Promotion";
        Promotion promotion = Promotion.builder()
            .title(promotionTitle)
            .build();
        sut.save(promotion);

        // Act
        boolean actual = sut.existsByTitle(promotionTitle);

        // Assert
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("promotion의 title과 일치하는 promotion을 조회한다.")
    void should_FindPromotion_by_PromotionTitle() {
        // Arrange
        String promotionTitle = "Test Promotion";

        Promotion promotion = Promotion.builder()
            .title(promotionTitle)
            .build();
        sut.save(promotion);

        // Act
        Optional<Promotion> actual = sut.findByTitle(promotionTitle);

        // Assert
        assertThat(actual.get().getTitle()).isEqualTo(promotionTitle);
    }
}