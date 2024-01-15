package topy.promotion.modules.promotion.dto;

import static topy.promotion.modules.common.Const.PROMOTION_DTO_NO_VALUES;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import topy.promotion.modules.promotion.domain.Promotion;

@Getter
public class RegisterPromotionRequest {

    @NotBlank(message = PROMOTION_DTO_NO_VALUES)
    private String title;

    @NotNull(message = PROMOTION_DTO_NO_VALUES)
    private LocalDateTime startDate;

    @NotNull(message = PROMOTION_DTO_NO_VALUES)
    private LocalDateTime endDate;

    public Promotion toPromotion() {
        return Promotion.builder()
            .title(title)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    }
}
