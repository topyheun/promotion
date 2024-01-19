package topy.promotion.modules.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import topy.promotion.modules.user.dto.UserSignUpRequest;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원가입 시 사용자가 이미 존재한다면 Exception 발생한다.")
    void should_Exception_when_UserExistsWhenSignUp() {
        // Arrange
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest("username", "password", "email@example.com");
        when(userRepository.existsByUsername(userSignUpRequest.getUsername())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.createAccount(userSignUpRequest));
    }
}