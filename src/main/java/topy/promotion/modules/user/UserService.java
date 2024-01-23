package topy.promotion.modules.user;

import static topy.promotion.modules.common.Const.USER_NOT_FOUND_ACCOUNT;
import static topy.promotion.modules.common.Const.USER_USED_ACCOUNT;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.user.dto.UserSignUpRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createAccount(UserSignUpRequest userSignUpRequest) {
        if (userRepository.existsByUsername(userSignUpRequest.getUsername())) {
            throw new RuntimeException(USER_USED_ACCOUNT);
        }
        User user = userSignUpRequest.toUser();
        userRepository.save(user);
    }

    public User findUserById(final Long userSq) {
        return userRepository.findById(userSq)
            .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ACCOUNT));
    }
}
