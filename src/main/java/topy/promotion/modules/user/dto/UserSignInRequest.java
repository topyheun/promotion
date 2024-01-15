package topy.promotion.modules.user.dto;

import static topy.promotion.modules.common.Const.USER_DTO_NO_PASSWORD;
import static topy.promotion.modules.common.Const.USER_DTO_NO_USERNAME;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserSignInRequest {

    @NotBlank(message = USER_DTO_NO_USERNAME)
    private String username;

    @NotBlank(message = USER_DTO_NO_PASSWORD)
    private String password;
}
