package topy.promotion.modules.promotion.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import topy.promotion.modules.common.BaseDateEntity;
import topy.promotion.modules.user.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Participation extends BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_sq")
    private Promotion promotion;

    @Builder
    public Participation(User user, Promotion promotion) {
        this.user = user;
        this.promotion = promotion;
    }
}
