package topy.promotion.modules.promotion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import topy.promotion.modules.promotion.domain.Winner;
import topy.promotion.modules.promotion.dto.SearchWinnerResponse;
import topy.promotion.modules.promotion.repository.WinnerRepository;

@ExtendWith(MockitoExtension.class)
class WinnerSearchServiceTest {

    @Mock
    private WinnerRepository winnerRepository;

    @InjectMocks
    private WinnerSearchService sut;

    @Test
    @DisplayName("우승자 목록을 리턴한다.")
    void should_ReturnWinnerList_when_Requested() {
        // Arrange
        String promotionTitle = "Test Promotion";
        Winner mockWinner = mock(Winner.class); // Winner 객체를 적절히 초기화
        List<Winner> winners = List.of(mockWinner);

        when(winnerRepository.findAllByParticipatedPromotionTitle(promotionTitle)).thenReturn(winners);

        // Act
        List<SearchWinnerResponse> result = sut.getWinners(promotionTitle);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(winners.size(), result.size());
    }
}