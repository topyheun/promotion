package topy.promotion.modules.promotion.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.domain.Winner;
import topy.promotion.modules.promotion.repository.WinnerRepository;
import topy.promotion.modules.user.domain.User;

@ExtendWith(MockitoExtension.class)
class WinnerCreateServiceTest {

    @Mock
    private WinnerRepository winnerRepository;

    @InjectMocks
    private WinnerCreateService sut;

    @Test
    @DisplayName("프로모션 제목과 사용자 그리고 보상이 제공될 때 우승자가 정보가 저장되어야 한다.")
    void should_SaveWinner_When_PromotionTitleAndUserAndRewardAreProvided() {
        // Arrange
        String promotionTitle = "Test Promotion";
        User mockUser = mock(User.class);
        Reward mockReward = mock(Reward.class);

        when(mockReward.getRank()).thenReturn(Rank.WIN);

        // Act
        sut.createWinner(promotionTitle, mockUser, mockReward);

        // Assert
        verify(winnerRepository).save(any(Winner.class));
    }
}