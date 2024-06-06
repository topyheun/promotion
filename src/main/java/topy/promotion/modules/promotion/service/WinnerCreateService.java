package topy.promotion.modules.promotion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.domain.Winner;
import topy.promotion.modules.promotion.repository.WinnerRepository;
import topy.promotion.modules.user.domain.User;

@Service
@Transactional
@RequiredArgsConstructor
public class WinnerCreateService {

    private final WinnerRepository winnerRepository;

    public void createWinner(String promotionTitle, User user, Reward reward) {
        Winner winner = Winner.builder()
            .winnerName(user.getUsername())
            .participatedPromotionTitle(promotionTitle)
            .winnerRank(reward.getRank().toString())
            .winnerReward(reward.getName())
            .build();
        winnerRepository.save(winner);
    }
}
