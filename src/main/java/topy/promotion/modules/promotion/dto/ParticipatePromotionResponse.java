package topy.promotion.modules.promotion.dto;

import lombok.Builder;

@Builder
public record ParticipatePromotionResponse(
    String promotionTitle,

    String winRank,

    String rewardName
) {

    public static ParticipatePromotionResponse of(String promotionTitle, String rewardName, String winRank) {
        return ParticipatePromotionResponse.builder()
            .promotionTitle(promotionTitle)
            .rewardName(rewardName)
            .winRank(winRank)
            .build();
    }
}
