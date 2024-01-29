package topy.promotion.modules.promotion.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import lombok.Builder;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.PromotionStatus;

@Builder
public record SearchPromotionResponse(
    String title,

    LocalDateTime startDate,

    LocalDateTime endDate,

    @Enumerated(EnumType.STRING)
    PromotionStatus status
) {

    public static SearchPromotionResponse of(Promotion promotion) {
        return SearchPromotionResponse.builder()
            .title(promotion.getTitle())
            .startDate(promotion.getStartDate())
            .endDate(promotion.getEndDate())
            .status(promotion.getStatus())
            .build();
    }
}
