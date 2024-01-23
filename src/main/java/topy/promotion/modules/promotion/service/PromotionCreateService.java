package topy.promotion.modules.promotion.service;

import static topy.promotion.modules.common.Const.PROMOTION_USED_PROMOTION;
import static topy.promotion.modules.common.Const.PROMOTION_WRONG_PROMOTION_PERIOD;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.dto.RegisterPromotionRequest;
import topy.promotion.modules.promotion.dto.RegisterPromotionResponse;
import topy.promotion.modules.promotion.repository.PromotionRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PromotionCreateService {

    private final PromotionRepository promotionRepository;

    public RegisterPromotionResponse createPromotion(RegisterPromotionRequest registerPromotionRequest) {
        if (promotionRepository.existsByTitle(registerPromotionRequest.getTitle())) {
            throw new RuntimeException(PROMOTION_USED_PROMOTION);
        }
        if (registerPromotionRequest.getStartDate().isAfter(registerPromotionRequest.getEndDate())) {
            throw new RuntimeException(PROMOTION_WRONG_PROMOTION_PERIOD);
        }

        Promotion promotion = registerPromotionRequest.toPromotion();
        promotionRepository.save(promotion);

        return RegisterPromotionResponse.of(promotion.getTitle());
    }
}
