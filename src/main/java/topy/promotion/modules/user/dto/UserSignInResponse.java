package topy.promotion.modules.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSignInResponse {

    private String username;

    @Builder
    public UserSignInResponse(String username) {
        this.username = username;
    }
}
