package topy.promotion.modules.promotion.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter네
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
}
