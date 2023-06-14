package topy.promotion.modules.promotion.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisterPromotionResponse {

    private String title;

    @Builder
    public RegisterPromotionResponse(String title) {
        this.title = title;
    }
}
