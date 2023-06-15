package topy.promotion.modules.promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.dto.*;

import java.util.ArrayList;
import java.util.List;

import static topy.promotion.modules.common.Const.*;

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

    public List<SearchPromotionResponse> getPromotion() {
        List<Promotion> promotions = promotionRepository.findAll();
        if (promotions.isEmpty()) {
            throw new RuntimeException(PROMOTION_NOTHING_REGISTERED_PROMOTION);
        }
        List<SearchPromotionResponse> promotionList = new ArrayList<>();

        for (Promotion promotion : promotions) {
            SearchPromotionResponse searchPromotionResponse = SearchPromotionResponse.builder()
                    .title(promotion.getTitle())
                    .startDate(promotion.getStartDate())
                    .endDate(promotion.getEndDate())
                    .status(promotion.getStatus())
                    .build();
            promotionList.add(searchPromotionResponse);
        }
        return promotionList;
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
