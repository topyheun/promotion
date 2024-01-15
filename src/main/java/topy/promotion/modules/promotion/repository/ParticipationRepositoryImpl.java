package topy.promotion.modules.promotion.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import topy.promotion.modules.promotion.domain.Participation;
import topy.promotion.modules.promotion.domain.QParticipation;

@Repository
@RequiredArgsConstructor
public class ParticipationRepositoryImpl implements ParticipationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Participation> checkUserParticipationInTodayPromotion(Long userSq, String promotionTitle) {
        QParticipation participation = QParticipation.participation;
        return Optional.ofNullable(jpaQueryFactory.selectFrom(participation)
            .where(participation.user.id.eq(userSq)
                .and(participation.createdAt.eq(LocalDate.now()))
                .and(participation.promotion.title.eq(promotionTitle)))
            .fetchOne());
    }
}
