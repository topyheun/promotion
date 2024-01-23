package topy.promotion.modules.promotion.service;

import static topy.promotion.modules.common.Const.PROMOTION_NOTHING_REGISTERED_PROMOTION;
import static topy.promotion.modules.common.Const.PROMOTION_NOT_FOUND_PROMOTION;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.dto.SearchPromotionResponse;
import topy.promotion.modules.promotion.repository.PromotionRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PromotionSearchService {

    private final PromotionRepository promotionRepository;

    public List<SearchPromotionResponse> getPromotion() {
        List<Promotion> promotions = promotionRepository.findAll();
        if (promotions.isEmpty()) {
            throw new RuntimeException(PROMOTION_NOTHING_REGISTERED_PROMOTION);
        }
        return promotions.stream()
            .map(SearchPromotionResponse::of)
            .collect(Collectors.toList());
    }

    public Promotion findPromotionByTitle(String promotionTitle) {
        return promotionRepository.findByTitle(promotionTitle)
            .orElseThrow(() -> new RuntimeException(PROMOTION_NOT_FOUND_PROMOTION));
    }
}
