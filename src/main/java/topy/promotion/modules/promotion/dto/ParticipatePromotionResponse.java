package topy.promotion.modules.promotion.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipatePromotionResponse {

    private String promotionTitle;

    private String winRank;

    private String rewardName;

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
