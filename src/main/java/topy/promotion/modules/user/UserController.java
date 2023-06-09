package topy.promotion.modules.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import topy.promotion.modules.user.dto.UserSignUpRequest;

import static topy.promotion.modules.common.Const.SUCCESS;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/sign-up")
    public ResponseEntity signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest) {
        userService.createAccount(userSignUpRequest);
        return ResponseEntity.ok(SUCCESS);
    }
}
