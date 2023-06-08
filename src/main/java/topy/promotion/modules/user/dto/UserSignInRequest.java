package topy.promotion.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static topy.promotion.modules.common.Const.USER_DTO_NO_PASSWORD;
import static topy.promotion.modules.common.Const.USER_DTO_NO_USERNAME;

@Getter
public class UserSignInRequest {

    @NotBlank(message = USER_DTO_NO_USERNAME)
    private String username;

    @NotBlank(message = USER_DTO_NO_PASSWORD)
    private String password;
}
