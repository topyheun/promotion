package topy.promotion.modules.promotion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.dto.RegisterPromotionRequest;
import topy.promotion.modules.promotion.dto.RegisterPromotionResponse;
import topy.promotion.modules.promotion.repository.PromotionRepository;

@ExtendWith(MockitoExtension.class)
class PromotionCreateServiceTest {

    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private PromotionCreateService promotionCreateService;

    @Test
    @DisplayName("등록 요청 시 프로모션을 저장한다.")
    void should_SaveNewPromotion_when_PromotionRegisterRequestReceived() {
        // Arrange
        String promotionTitle = "Hot Sale";

        RegisterPromotionRequest request = new RegisterPromotionRequest(promotionTitle, LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        when(promotionRepository.existsByTitle(anyString())).thenReturn(false);

        // Act
        RegisterPromotionResponse response = promotionCreateService.createPromotion(request);

        // Assert
        assertEquals(promotionTitle, response.title());
        verify(promotionRepository).save(any(Promotion.class));
    }

    @Test
    @DisplayName("현재 진행 중인 프로모션과 프로모션 이름이 일치하는 경우 RuntimeException이 발생한다.")
    void should_ThrowException_when_RegisterAlreadyExistsPromotion() {
        // Arrange
        String promotionTitle = "Hot Sale";

        RegisterPromotionRequest request = new RegisterPromotionRequest(promotionTitle, LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        when(promotionRepository.existsByTitle(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> promotionCreateService.createPromotion(request));
    }

    @Test
    @DisplayName("프로모션 등록 시 프로모션 시작일과 마감일을 잘못 기입했을 경우 RuntimeException이 발생한다.")
    void should_ThrowException_when_RegisterWrongPeriodPromotion() {
        // Arrange
        String promotionTitle = "Hot Sale";
        RegisterPromotionRequest request = new RegisterPromotionRequest(promotionTitle, LocalDateTime.now().plusDays(10), LocalDateTime.now());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> promotionCreateService.createPromotion(request));
    }
}