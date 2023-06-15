package topy.promotion.modules.promotion.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import topy.promotion.modules.promotion.domain.PromotionStatus;

import java.time.LocalDateTime;

@Getter
public class SearchPromotionResponse {

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private PromotionStatus status;

    @Builder
    public SearchPromotionResponse(String title, LocalDateTime startDate, LocalDateTime endDate, PromotionStatus status) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
}
