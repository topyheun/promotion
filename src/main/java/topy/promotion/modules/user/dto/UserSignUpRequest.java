package topy.promotion.modules.user.dto;

import static topy.promotion.modules.common.Const.USER_DTO_NO_EMAIL;
import static topy.promotion.modules.common.Const.USER_DTO_NO_PASSWORD;
import static topy.promotion.modules.common.Const.USER_DTO_NO_USERNAME;
import static topy.promotion.modules.common.Const.USER_DTO_WRONG_EMAIL_FORMAT;
import static topy.promotion.modules.common.Const.USER_DTO_WRONG_PASSWORD_FORMAT;
import static topy.promotion.modules.common.Const.USER_DTO_WRONG_USERNAME_FORMAT;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import topy.promotion.modules.user.User;

@Builder
public record UserSignUpRequest(
    @NotBlank(message = USER_DTO_NO_USERNAME) @Pattern(regexp = "^(?=.*[a-z])[a-z0-9]{4,12}$", message = USER_DTO_WRONG_USERNAME_FORMAT)
    String username,

    @NotBlank(message = USER_DTO_NO_PASSWORD) @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=~`\\[\\]{}|:;\"'<>,.?\\\\/])[A-Za-z\\d!@#$%^&*()_\\-+=~`\\[\\]{}|:;\"'<>,.?\\\\/]{8,20}$", message = USER_DTO_WRONG_PASSWORD_FORMAT)
    String password,

    @NotBlank(message = USER_DTO_NO_EMAIL) @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = USER_DTO_WRONG_EMAIL_FORMAT)
    String email
) {

    public User toUser() {
        return User.builder()
            .username(username)
            .password(password)
            .email(email)
            .build();
    }
}
