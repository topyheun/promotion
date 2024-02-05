package topy.promotion.modules.promotion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.infra.aop.DistributedLock;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.repository.RewardRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RewardSearchService {

    private final RewardRepository rewardRepository;
    private final RewardCreateRankService rewardCreateRankService;

    @DistributedLock(key = "#promotionTitle")
    public Reward getWinnerReward(String promotionTitle) {
        Reward reward = getReward(promotionTitle);
        reward.decreaseQuantity();
        return reward;
    }

    public Reward getReward(final String promotionTitle) {
        Rank rank = rewardCreateRankService.createRank();
        return rewardRepository.getAvailableReward(rank, promotionTitle);
    }
}
