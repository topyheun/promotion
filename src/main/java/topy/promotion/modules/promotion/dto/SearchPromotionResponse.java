package topy.promotion.modules.promotion.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.PromotionStatus;

@Data
public class SearchPromotionResponse {

    private final String title;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private final PromotionStatus status;

    @Builder
    public SearchPromotionResponse(String title, LocalDateTime startDate, LocalDateTime endDate, PromotionStatus status) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public static SearchPromotionResponse of(Promotion promotion) {
        return SearchPromotionResponse.builder()
            .title(promotion.getTitle())
            .startDate(promotion.getStartDate())
            .endDate(promotion.getEndDate())
            .status(promotion.getStatus())
            .build();
    }
}
