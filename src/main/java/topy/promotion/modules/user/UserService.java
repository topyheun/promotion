package topy.promotion.modules.user;

import static topy.promotion.modules.common.Const.USER_USED_ACCOUNT;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.user.dto.UserSignUpRequest;

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
}
