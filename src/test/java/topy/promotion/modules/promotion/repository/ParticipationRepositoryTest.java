package topy.promotion.modules.promotion.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import topy.promotion.modules.promotion.domain.Participation;
import topy.promotion.modules.user.domain.User;

@ActiveProfiles("test")
@DataJpaTest
class ParticipationRepositoryTest {

    @Autowired
    private ParticipationRepository sut;

    @Test
    @DisplayName("user가 금일 promotion에 참가하지 않았다면 true 반환한다.")
    public void should_ReturnTrue_when_NotAttendPromotion() {
        // Arrange
        Long userSq = 1L;
        String promotionTitle = "Test Promotion";

        User user = User.builder()
            .username("LSH")
            .build();

        Participation participation = Participation.builder()
            .user(user)
            .build();
        sut.save(participation);

        // Act
        Optional<Participation> actual = sut.checkUserParticipationInTodayPromotion(userSq, promotionTitle);

        // Assert
        assertThat(actual).isEmpty();
    }
}