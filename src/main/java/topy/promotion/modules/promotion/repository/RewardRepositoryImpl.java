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
        QReward reward = QReward.reward;
        return jpaQueryFactory.selectFrom(reward)
                .where(reward.rank.eq(rank)
                        .and(reward.promotion.title.eq(promotionTitle))
                        .and(reward.quantity.goe(1)))
                .fetchOne();
    }
}
