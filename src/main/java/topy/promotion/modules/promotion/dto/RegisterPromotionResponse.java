package topy.promotion.modules.promotion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPromotionResponse {

    private String title;

    public static RegisterPromotionResponse of(String title) {
        return new RegisterPromotionResponse(title);
    }
}
