package topy.promotion.modules.promotion.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import topy.promotion.modules.promotion.domain.Rank;

@Builder
public record RegisterRewardResponse(
    String name,

    int quantity,

    @Enumerated(EnumType.STRING)
    Rank rank
) {

}
