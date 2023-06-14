package topy.promotion.modules.promotion.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import topy.promotion.modules.promotion.domain.Rank;

import static topy.promotion.modules.common.Const.REWARD_DTO_ENTER_NUMBER_GREATER_THAN_ZERO;
import static topy.promotion.modules.common.Const.REWARD_DTO_NO_VALUES;

@Getter
public class RegisterRewardRequest {

    @NotBlank(message = REWARD_DTO_NO_VALUES)
    private String name;

    @NotNull(message = REWARD_DTO_NO_VALUES)
    @Positive(message = REWARD_DTO_ENTER_NUMBER_GREATER_THAN_ZERO)
    private int quantity;

    @NotNull(message = REWARD_DTO_NO_VALUES)
    @Enumerated(EnumType.STRING)
    private Rank rank;
}
