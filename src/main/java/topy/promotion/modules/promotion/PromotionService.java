package topy.promotion.modules.promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.dto.RegisterPromotionRequest;
import topy.promotion.modules.promotion.dto.RegisterPromotionResponse;

import static topy.promotion.modules.common.Const.PROMOTION_USED_PROMOTION;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

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
}
