package topy.promotion.modules.promotion.repository;

import java.util.Optional;
import topy.promotion.modules.promotion.domain.Participation;

public interface ParticipationRepositoryCustom {

    Optional<Participation> checkUserParticipationInTodayPromotion(Long userSq, String promotionTitle);
}
