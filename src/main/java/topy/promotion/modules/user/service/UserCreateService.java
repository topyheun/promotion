package topy.promotion.modules.user.service;

import static topy.promotion.modules.common.Const.USER_USED_ACCOUNT;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.user.domain.User;
import topy.promotion.modules.user.repository.UserRepository;
import topy.promotion.modules.user.dto.UserSignUpRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCreateService {

    private final UserRepository userRepository;

    public void createAccount(UserSignUpRequest userSignUpRequest) {
        if (userRepository.existsByUsername(userSignUpRequest.username())) {
            throw new RuntimeException(USER_USED_ACCOUNT);
        }
        User user = userSignUpRequest.toUser();
        userRepository.save(user);
    }
}
