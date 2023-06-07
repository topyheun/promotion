package topy.promotion.modules.promotion;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "reward")
    private List<Winner> winners = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_sq")
    private Promotion promotion;
}
