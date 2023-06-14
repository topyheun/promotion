package topy.promotion.modules.promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.dto.RegisterPromotionRequest;
import topy.promotion.modules.promotion.dto.RegisterPromotionResponse;
import topy.promotion.modules.promotion.dto.RegisterRewardRequest;
import topy.promotion.modules.promotion.dto.RegisterRewardResponse;

import static topy.promotion.modules.common.Const.PROMOTION_NOT_FOUND_PROMOTION;
import static topy.promotion.modules.common.Const.PROMOTION_USED_PROMOTION;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final RewardRepository rewardRepository;

    @Transactional
    public RegisterPromotionResponse createPromotion(RegisterPromotionRequest registerPromotionRequest) {
        if (promotionRepository.existsByTitle(registerPromotionRequest.getTitle())) {
            throw new RuntimeException(PROMOTION_USED_PROMOTION);
        }

        Promotion promotion = Promotion.builder()
                .title(registerPromotionRequest.getTitle())
                .startDate(registerPromotionRequest.getStartDate())
                .endDate(registerPromotionRequest.getEndDate())
                .build();
        promotionRepository.save(promotion);

        return RegisterPromotionResponse.builder()
                .title(registerPromotionRequest.getTitle())
                .build();
    }

    @Transactional
    public RegisterRewardResponse createReward(String promotion_title, RegisterRewardRequest registerRewardRequest) {
        Promotion promotion = promotionRepository.findByTitle(promotion_title)
                .orElseThrow(() -> new RuntimeException(PROMOTION_NOT_FOUND_PROMOTION));

        Reward reward = Reward.builder()
                .name(registerRewardRequest.getName())
                .quantity(registerRewardRequest.getQuantity())
                .rank(registerRewardRequest.getRank())
                .promotion(promotion)
                .build();
        rewardRepository.save(reward);

        return RegisterRewardResponse.builder()
                .name(registerRewardRequest.getName())
                .quantity(registerRewardRequest.getQuantity())
                .rank(registerRewardRequest.getRank())
                .build();
    }
}
