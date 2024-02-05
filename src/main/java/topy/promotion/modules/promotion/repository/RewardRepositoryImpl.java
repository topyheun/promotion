package topy.promotion.modules.promotion.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import topy.promotion.modules.promotion.domain.QReward;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;

@Repository
@RequiredArgsConstructor
public class RewardRepositoryImpl implements RewardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Reward getAvailableReward(Rank rank, String promotionTitle) {
        if (rank == Rank.BOOM) {
            return Reward.builder()
                .name("꽝")
                .rank(Rank.BOOM)
                .build();
        }
        QReward qReward = QReward.reward;
        Reward reward = jpaQueryFactory.selectFrom(qReward)
            .where(qReward.rank.eq(rank)
                .and(qReward.promotion.title.eq(promotionTitle))
                .and(qReward.quantity.goe(1)))
            .fetchOne();

        if (reward == null) {
            return Reward.builder()
                .name("꽝")
                .rank(Rank.BOOM)
                .build();
        }
        return reward;
    }
}
