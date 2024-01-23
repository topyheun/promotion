package topy.promotion.modules.promotion.service;

import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.repository.RewardRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RewardSearchService {

    private final RewardRepository rewardRepository;

    public Reward getReward(final String promotionTitle) {
        SecureRandom secureRandom = new SecureRandom();
        int randomNumber = secureRandom.nextInt(100) + 1;

        Rank rank;
        if (randomNumber <= 10) {
            rank = Rank.FIRST;
        } else if (randomNumber <= 25) {
            rank = Rank.SECOND;
        } else if (randomNumber <= 45) {
            rank = Rank.THIRD;
        } else {
            return null;
        }
        return rewardRepository.getAvailableReward(rank, promotionTitle);
    }
}
