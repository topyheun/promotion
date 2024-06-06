package topy.promotion.modules.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import topy.promotion.infra.config.QuerydslConfig;
import topy.promotion.modules.user.domain.User;
import topy.promotion.modules.user.repository.UserRepository;

@Import(QuerydslConfig.class)
@ActiveProfiles("test")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository sut;

    @Test
    @DisplayName("등록하려는 이름의 사용자가 이미 존재한다면 true를 리턴한다.")
    public void should_ReturnTrue_when_UserExists() {
        // Arrange
        String username = "LSH";

        User user = User.builder()
            .username(username)
            .build();
        sut.save(user);

        // Act
        boolean actual = sut.existsByUsername(username);

        // Assert
        assertThat(actual).isTrue();
    }
}