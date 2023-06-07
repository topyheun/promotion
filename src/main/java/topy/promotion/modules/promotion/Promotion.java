package topy.promotion.modules.promotion;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "promotion_title")
    private String title;

    @Column(name = "promotion_start_date")
    private LocalDateTime startDate;

    @Column(name = "promotion_end_date")
    private LocalDateTime endDate;

    @Column(name = "promotion_status")
    @Enumerated(EnumType.STRING)
    private PromotionStatus status;

    @OneToMany(mappedBy = "promotion")
    private List<Participation> participations = new ArrayList<>();

    @OneToMany(mappedBy = "promotion")
    private List<Winner> winners = new ArrayList<>();

    @OneToMany(mappedBy = "promotion")
    private List<Reward> rewards = new ArrayList<>();
}
