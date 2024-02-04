package topy.promotion.modules.user.dto;

import static topy.promotion.modules.common.Const.USER_DTO_NO_USERNAME;
import static topy.promotion.modules.common.Const.USER_DTO_WRONG_USERNAME_FORMAT;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import topy.promotion.modules.user.User;

@Builder
public record UserSignUpRequest(
    @NotBlank(message = USER_DTO_NO_USERNAME) @Pattern(regexp = "^(?=.*[a-z])[a-z0-9]{4,12}$", message = USER_DTO_WRONG_USERNAME_FORMAT)
    String username
) {

    public User toUser() {
        return User.builder()
            .username(username)
            .build();
    }
}
