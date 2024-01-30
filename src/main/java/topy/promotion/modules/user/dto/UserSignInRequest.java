package topy.promotion.modules.user.dto;

import static topy.promotion.modules.common.Const.USER_DTO_NO_PASSWORD;
import static topy.promotion.modules.common.Const.USER_DTO_NO_USERNAME;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserSignInRequest(
    @NotBlank(message = USER_DTO_NO_USERNAME)
    String username,

    @NotBlank(message = USER_DTO_NO_PASSWORD)
    String password
) {

}
