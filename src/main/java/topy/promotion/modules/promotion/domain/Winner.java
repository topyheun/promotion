package topy.promotion.modules.promotion.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import topy.promotion.modules.common.BaseDateEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Winner extends BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "winner_name")
    private String winnerName;

    @Column(name = "winner_participated_promotion")
    private String participatedPromotionTitle;

    @Column(name = "winner_rank")
    private String winnerRank;

    @Column(name = "winner_reward")
    private String winnerReward;

    @Builder
    public Winner(String winnerName, String participatedPromotionTitle, String winnerRank,
        String winnerReward) {
        this.winnerName = winnerName;
        this.participatedPromotionTitle = participatedPromotionTitle;
        this.winnerRank = winnerRank;
        this.winnerReward = winnerReward;
    }
}
