package topy.promotion.modules.user;

import static topy.promotion.modules.common.Const.USER_NOT_FOUND_ACCOUNT;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserSearchService {

    private final UserRepository userRepository;

    public User findUserById(final Long userSq) {
        return userRepository.findById(userSq)
            .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ACCOUNT));
    }
}
