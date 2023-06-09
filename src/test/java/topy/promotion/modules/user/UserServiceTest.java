package topy.promotion.modules.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import topy.promotion.modules.user.dto.UserSignInRequest;
import topy.promotion.modules.user.dto.UserSignUpRequest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldNotSaveUserIfExceptionISThrownDuringSignUp() {
        // Arrange
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
        ReflectionTestUtils.setField(userSignUpRequest, "username", "john");
        ReflectionTestUtils.setField(userSignUpRequest, "password", "password");
        ReflectionTestUtils.setField(userSignUpRequest, "email", "john@example.com");


        when(userRepository.existsByUsername("john")).thenReturn(true);

        // Act and Assert
        Assertions.assertThrows(RuntimeException.class, () -> userService.createAccount(userSignUpRequest));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionIfUserDoesNotExistDuringSignIn() {
        // Arrange
        UserSignInRequest userSignInRequest = new UserSignInRequest();
        ReflectionTestUtils.setField(userSignInRequest, "username", "john");
        ReflectionTestUtils.setField(userSignInRequest, "password", "password");

        when(userRepository.findByUsername("john")).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(RuntimeException.class, () -> userService.signIn(userSignInRequest));
    }

    @Test
    void shouldThrowExceptionIfWrongPasswordIsEnteredDuringSignIn() {
        // Arrange
        UserSignInRequest userSignInRequest = new UserSignInRequest();
        ReflectionTestUtils.setField(userSignInRequest, "username", "john");
        ReflectionTestUtils.setField(userSignInRequest, "password", "password");

        User user = User.builder()
                .username("john")
                .password("wrong_password")
                .build();

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));

        // Act and Assert
        Assertions.assertThrows(RuntimeException.class, () -> userService.signIn(userSignInRequest));
    }
}
