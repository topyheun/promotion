package topy.promotion.modules.promotion.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
    private Rank winnerRank;
}
