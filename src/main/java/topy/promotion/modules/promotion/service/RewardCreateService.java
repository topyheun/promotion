package topy.promotion.modules.promotion.service;

import static topy.promotion.modules.common.Const.REWARD_DUPLICATE_REWARD;

import java.util.List;
import java.util.stream.Collectors;
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

    public List<RegisterRewardResponse> createRewards(final String promotionTitle, List<RegisterRewardRequest> registerRewardRequests) {
        Promotion promotion = promotionSearchService.findPromotionByTitle(promotionTitle);
        checkExistsReward(promotionTitle, registerRewardRequests);

        List<Reward> rewards = registerRewardRequests.stream()
            .map(registerRewardRequest -> registerRewardRequest.toReward(promotion))
            .collect(Collectors.toList());
        rewardRepository.saveAll(rewards);

        List<RegisterRewardResponse> registerRewardResponses = registerRewardRequests.stream()
            .map(RegisterRewardRequest::toResponse)
            .collect(Collectors.toList());
        return registerRewardResponses;
    }

    private void checkExistsReward(final String promotionTitle, List<RegisterRewardRequest> registerRewardRequests) {
        for (final RegisterRewardRequest registerRewardRequest : registerRewardRequests) {
            if (rewardRepository.existsByNameAndPromotion_Title(registerRewardRequest.getName(), promotionTitle)) {
                throw new RuntimeException(REWARD_DUPLICATE_REWARD);
            }
        }
    }
}
