package topy.promotion.modules.promotion.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Winner;
import topy.promotion.modules.promotion.dto.SearchWinnerResponse;
import topy.promotion.modules.promotion.repository.WinnerRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WinnerSearchService {

    private final WinnerRepository winnerRepository;

    public List<SearchWinnerResponse> getWinners(final String promotionTitle) {
        List<Winner> winners = winnerRepository.findAllByParticipatedPromotionTitle(promotionTitle);

        return winners.stream()
            .map(SearchWinnerResponse::toResponse)
            .collect(Collectors.toList());
    }
}
