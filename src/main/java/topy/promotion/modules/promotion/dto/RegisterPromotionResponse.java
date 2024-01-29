package topy.promotion.modules.promotion.dto;

import lombok.Builder;

@Builder
public record RegisterPromotionResponse(
    String title
) {

    public static RegisterPromotionResponse of(String title) {
        return new RegisterPromotionResponse(title);
    }
}
