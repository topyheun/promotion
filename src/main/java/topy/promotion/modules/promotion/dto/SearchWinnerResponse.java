package topy.promotion.modules.promotion.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchWinnerResponse {

    private String winnerName;

    private String winnerRank;

    private String winnerReward;

    @Builder
    public SearchWinnerResponse(String winnerName, String winnerRank, String winnerReward) {
        this.winnerName = winnerName;
        this.winnerRank = winnerRank;
        this.winnerReward = winnerReward;
    }

    public static SearchWinnerResponse toResponse(Winner winner) {
        return SearchWinnerResponse.builder()
            .winnerName(winner.getWinnerName())
            .winnerRank(winner.getWinnerRank())
            .winnerReward(winner.getWinnerReward())
            .build();
    }
}
