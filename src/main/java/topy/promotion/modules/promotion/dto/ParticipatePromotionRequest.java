package topy.promotion.modules.promotion.dto;

import lombok.Builder;

@Builder
public record ParticipatePromotionRequest(
    Long userSq
) {

}
