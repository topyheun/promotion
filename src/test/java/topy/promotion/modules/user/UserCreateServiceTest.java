package topy.promotion.modules.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import topy.promotion.modules.user.dto.UserSignUpRequest;
import topy.promotion.modules.user.repository.UserRepository;
import topy.promotion.modules.user.service.UserCreateService;

@ExtendWith(MockitoExtension.class)
class UserCreateServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserCreateService userCreateService;

    @Test
    @DisplayName("회원가입 시 사용자가 이미 존재한다면 Exception 발생한다.")
    void should_ThrowException_when_UserExistsWhenSignUp() {
        // Arrange
        String username = "Topy";
        
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest(username);
        when(userRepository.existsByUsername(userSignUpRequest.username())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userCreateService.createAccount(userSignUpRequest));
    }
}