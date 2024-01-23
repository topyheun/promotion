package topy.promotion.modules.promotion.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ParticipatePromotionResponse {

    private final String promotionTitle;

    private final String winRank;

    private final String rewardName;

    @Builder
    public ParticipatePromotionResponse(String promotionTitle, String winRank, String rewardName) {
        this.promotionTitle = promotionTitle;
        this.winRank = winRank;
        this.rewardName = rewardName;
    }

    public static ParticipatePromotionResponse of(String promotionTitle, String rewardName, String winRank) {
        return ParticipatePromotionResponse.builder()
            .promotionTitle(promotionTitle)
            .rewardName(rewardName)
            .winRank(winRank)
            .build();
    }
}
