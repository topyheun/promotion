package topy.promotion.modules.promotion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static topy.promotion.modules.common.Const.PROMOTION_NOTHING_REGISTERED_PROMOTION;
import static topy.promotion.modules.common.Const.PROMOTION_NOT_FOUND_PROMOTION;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.dto.SearchPromotionResponse;
import topy.promotion.modules.promotion.repository.PromotionRepository;

@ExtendWith(MockitoExtension.class)
class PromotionSearchServiceTest {

    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private PromotionSearchService promotionSearchService;

    @Test
    @DisplayName("프로모션 목록을 리턴한다.")
    void should_ReturnPromotionList_when_Requested() {
        // Arrange
        Promotion promotion = new Promotion("Hot Sale", LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        when(promotionRepository.findAll()).thenReturn(Arrays.asList(promotion));

        // Act
        List<SearchPromotionResponse> promotions = promotionSearchService.getPromotion();

        // Assert
        assertFalse(promotions.isEmpty());
        assertEquals(1, promotions.size());
    }

    @Test
    @DisplayName("빈 프로젝트 목록이 리턴될 경우 RuntimeException이 발생한다.")
    void should_ThrowException_when_EmptyPromotionList() {
        // Arrange
        when(promotionRepository.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> promotionSearchService.getPromotion());
        assertEquals(PROMOTION_NOTHING_REGISTERED_PROMOTION, exception.getMessage());
    }

    @Test
    @DisplayName("프로모션을 찾을 수 없을 경우 RuntimeException이 발생한다.")
    void should_ThrowException_when_SearchPromotionByTitle() {
        // Arrange
        String promotionTitle = "Hot Sale";
        when(promotionRepository.findByTitle(promotionTitle)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> promotionSearchService.findPromotionByTitle(promotionTitle));
        assertEquals(PROMOTION_NOT_FOUND_PROMOTION, exception.getMessage());
    }
}