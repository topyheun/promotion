package topy.promotion.modules.promotion.dto;

import lombok.Getter;

@Getter
public class RegisterPromotionResponse {

    private final String title;

    public RegisterPromotionResponse(String title) {
        this.title = title;
    }

    public static RegisterPromotionResponse of(String title) {
        return new RegisterPromotionResponse(title);
    }
}
