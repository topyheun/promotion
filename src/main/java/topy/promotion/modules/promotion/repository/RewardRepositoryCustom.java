package topy.promotion.modules.promotion.repository;

import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;

public interface RewardRepositoryCustom {

    Reward getAvailableReward(Rank first, String promotionTitle);
}
