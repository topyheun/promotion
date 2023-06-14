package topy.promotion.modules.promotion.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import topy.promotion.modules.promotion.PromotionRepository;
import topy.promotion.modules.promotion.domain.Promotion;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PromotionScheduler {

    private final PromotionRepository promotionRepository;

    @Scheduled(fixedRate = 60000) // 1분마다 실행
    public void checkPromotionDate() {
        LocalDateTime now = LocalDateTime.now();

        List<Promotion> promotions = promotionRepository.findAll();

        for (Promotion promotion : promotions) {
            if (now.isAfter(promotion.getStartDate()) && now.isBefore(promotion.getEndDate())) {
                promotion.startPromotion();
            } else {
                promotion.endPromotion();
            }
            promotionRepository.save(promotion);
        }
    }
}
