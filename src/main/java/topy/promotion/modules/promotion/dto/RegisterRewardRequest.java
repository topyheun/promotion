package topy.promotion.modules.promotion.dto;

import static topy.promotion.modules.common.Const.REWARD_DTO_ENTER_NUMBER_GREATER_THAN_ZERO;
import static topy.promotion.modules.common.Const.REWARD_DTO_NO_VALUES;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;

@Builder
public record RegisterRewardRequest(
    @NotBlank(message = REWARD_DTO_NO_VALUES)
    String name,

    @NotNull(message = REWARD_DTO_NO_VALUES) @Positive(message = REWARD_DTO_ENTER_NUMBER_GREATER_THAN_ZERO)
    int quantity,

    @Enumerated(EnumType.STRING) @NotNull(message = REWARD_DTO_NO_VALUES)
    Rank rank
) {

    public Reward toReward(Promotion promotion) {
        return Reward.builder()
            .name(name)
            .quantity(quantity)
            .rank(rank)
            .promotion(promotion)
            .build();
    }

    public RegisterRewardResponse toResponse() {
        return RegisterRewardResponse.builder()
            .name(name)
            .quantity(quantity)
            .rank(rank)
            .build();
    }
}
