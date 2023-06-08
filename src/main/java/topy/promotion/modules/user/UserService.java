package topy.promotion.modules.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.user.dto.UserSignInRequest;
import topy.promotion.modules.user.dto.UserSignInResponse;
import topy.promotion.modules.user.dto.UserSignUpRequest;

import static topy.promotion.modules.common.Const.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createAccount(UserSignUpRequest userSignUpRequest) {
        if (userRepository.existsByUsername(userSignUpRequest.getUsername())) {
            throw new RuntimeException(USER_USED_ACCOUNT);
        }

        User user = User.builder()
                .username(userSignUpRequest.getUsername())
                .password(userSignUpRequest.getPassword())
                .email(userSignUpRequest.getEmail())
                .build();
        userRepository.save(user);
    }

    public UserSignInResponse signIn(UserSignInRequest userSignInRequest) {
        User user = userRepository.findByUsername(userSignInRequest.getUsername())
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ACCOUNT));

        if (user.getPassword().equals(userSignInRequest.getPassword())) {
            UserSignInResponse userSignInResponse = UserSignInResponse.builder()
                    .username(userSignInRequest.getUsername())
                    .build();
            return userSignInResponse;
        } else {
            throw new RuntimeException(USER_INCORRECT_PASSWORD);
        }
    }
}
