package topy.promotion.modules.promotion.service;

import static topy.promotion.modules.common.Const.REWARD_DUPLICATE_REWARD;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.dto.RegisterRewardRequest;
import topy.promotion.modules.promotion.dto.RegisterRewardResponse;
import topy.promotion.modules.promotion.repository.RewardRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RewardCreateService {

    private final RewardRepository rewardRepository;
    private final PromotionSearchService promotionSearchService;

    public RegisterRewardResponse createRewards(final String promotionTitle, RegisterRewardRequest registerRewardRequest) {
        Promotion promotion = promotionSearchService.findPromotionByTitle(promotionTitle);
        checkExistsReward(promotionTitle);

        Reward reward = registerRewardRequest.toReward(promotion);
        rewardRepository.save(reward);

        return registerRewardRequest.toResponse();
    }

    private void checkExistsReward(final String promotionTitle) {
        if (rewardRepository.existsByPromotion_Title(promotionTitle)) {
            throw new RuntimeException(REWARD_DUPLICATE_REWARD);
        }
    }
}
