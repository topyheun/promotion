package topy.promotion.modules.promotion.dto;

import lombok.Builder;
import topy.promotion.modules.promotion.domain.Winner;

@Builder
public record SearchWinnerResponse(
    String winnerName,

    String winnerRank,

    String winnerReward
) {

    public static SearchWinnerResponse toResponse(Winner winner) {
        return SearchWinnerResponse.builder()
            .winnerName(winner.getWinnerName())
            .winnerRank(winner.getWinnerRank())
            .winnerReward(winner.getWinnerReward())
            .build();
    }
}
