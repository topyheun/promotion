package topy.promotion.modules.promotion.dto;

import static topy.promotion.modules.common.Const.PROMOTION_DTO_NO_VALUES;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import topy.promotion.modules.promotion.domain.Promotion;

@Builder
public record RegisterPromotionRequest(
    @NotBlank(message = PROMOTION_DTO_NO_VALUES)
    String title,

    @NotNull(message = PROMOTION_DTO_NO_VALUES)
    LocalDateTime startDate,

    @NotNull(message = PROMOTION_DTO_NO_VALUES)
    LocalDateTime endDate
) {

    public Promotion toPromotion() {
        return Promotion.builder()
            .title(title)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    }
}
