package topy.promotion.modules.promotion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

import static topy.promotion.modules.common.Const.PROMOTION_DTO_NO_VALUES;

@Getter
public class RegisterPromotionRequest {

    @NotBlank(message = PROMOTION_DTO_NO_VALUES)
    private String title;

    @NotNull(message = PROMOTION_DTO_NO_VALUES)
    private LocalDateTime startDate;

    @NotNull(message = PROMOTION_DTO_NO_VALUES)
    private LocalDateTime endDate;
}
