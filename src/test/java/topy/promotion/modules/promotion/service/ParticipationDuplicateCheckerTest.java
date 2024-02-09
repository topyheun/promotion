package topy.promotion.modules.promotion.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import topy.promotion.modules.promotion.repository.ParticipationRepository;

@ExtendWith(MockitoExtension.class)
class ParticipationDuplicateCheckerTest {

    @Mock
    private ParticipationRepository participationRepository;

    @InjectMocks
    private ParticipationDuplicateChecker participationDuplicateChecker;

    @Test
    @DisplayName("오늘 프로모션에 사용자가 참여하지 않았다면 예외가 발생하지 않는다.")
    void should_NoThrowException_when_UserHasNotParticipatedToday() {
        // Arrange
        Long userSq = 1L;
        String promotionTitle = "Test Promotion";

        when(participationRepository.checkUserParticipationInTodayPromotion(userSq, promotionTitle)).thenReturn(Optional.empty());

        // Act & Assert
        assertDoesNotThrow(() -> participationDuplicateChecker.checkDuplicateParticipation(userSq, promotionTitle));
    }
}