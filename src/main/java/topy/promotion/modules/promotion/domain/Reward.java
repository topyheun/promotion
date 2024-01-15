package topy.promotion.modules.promotion.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reward_name")
    private String name;

    @Column(name = "reward_quantity")
    private int quantity;

    @Column(name = "reward_rank")
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_sq")
    private Promotion promotion;

    @Builder
    public Reward(String name, int quantity, Rank rank, Promotion promotion) {
        this.name = name;
        this.quantity = quantity;
        this.rank = rank;
        this.promotion = promotion;
    }

    public void decreaseQuantity() {
        this.quantity -= 1;
    }
}
