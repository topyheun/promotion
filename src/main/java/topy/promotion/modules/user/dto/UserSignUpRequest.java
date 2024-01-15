package topy.promotion.modules.user.dto;

import static topy.promotion.modules.common.Const.USER_DTO_NO_EMAIL;
import static topy.promotion.modules.common.Const.USER_DTO_NO_PASSWORD;
import static topy.promotion.modules.common.Const.USER_DTO_NO_USERNAME;
import static topy.promotion.modules.common.Const.USER_DTO_WRONG_EMAIL_FORMAT;
import static topy.promotion.modules.common.Const.USER_DTO_WRONG_PASSWORD_FORMAT;
import static topy.promotion.modules.common.Const.USER_DTO_WRONG_USERNAME_FORMAT;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = USER_DTO_NO_USERNAME)
    @Pattern(regexp = "^(?=.*[a-z])[a-z0-9]{0,10}$", message = USER_DTO_WRONG_USERNAME_FORMAT)
    private String username;

    @NotBlank(message = USER_DTO_NO_PASSWORD)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=~`\\[\\]{}|:;\"'<>,.?\\\\/])[A-Za-z\\d!@#$%^&*()_\\-+=~`\\[\\]{}|:;\"'<>,.?\\\\/]{8,20}$", message = USER_DTO_WRONG_PASSWORD_FORMAT)
    private String password;

    @NotBlank(message = USER_DTO_NO_EMAIL)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = USER_DTO_WRONG_EMAIL_FORMAT)
    private String email;
}
