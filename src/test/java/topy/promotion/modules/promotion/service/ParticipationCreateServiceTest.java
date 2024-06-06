package topy.promotion.modules.promotion.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import topy.promotion.modules.promotion.domain.Participation;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.repository.ParticipationRepository;
import topy.promotion.modules.user.domain.User;

@ExtendWith(MockitoExtension.class)
class ParticipationCreateServiceTest {

    @Mock
    private ParticipationRepository participationRepository;

    @InjectMocks
    private ParticipationCreateService sut;

    @Test
    @DisplayName("사용자와 프로모션이 제공될 때 참여 정보가 저장되어야 한다.")
    void should_SaveParticipation_When_UserAndPromotionAreProvided() {
        // Arrange
        User mockUser = mock(User.class);
        Promotion mockPromotion = mock(Promotion.class);

        // Act
        sut.saveParticipation(mockUser, mockPromotion);

        // Assert
        verify(participationRepository).save(any(Participation.class));
    }
}