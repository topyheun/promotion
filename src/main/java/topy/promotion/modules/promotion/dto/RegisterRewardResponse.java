package topy.promotion.modules.promotion.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import topy.promotion.modules.promotion.domain.Rank;

@Getter
public class RegisterRewardResponse {

    private String name;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Builder
    public RegisterRewardResponse(String name, int quantity, Rank rank) {
        this.name = name;
        this.quantity = quantity;
        this.rank = rank;
    }
}
