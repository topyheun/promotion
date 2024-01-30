package topy.promotion.modules.user.dto;

import lombok.Builder;

@Builder
public record UserSignInResponse(
    String username
) {

}
