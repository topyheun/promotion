package topy.promotion.modules.promotion.service;

import static topy.promotion.modules.common.Const.PROMOTION_DUPLICATE_PARTICIPATION_PROMOTION;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.repository.ParticipationRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParticipationDuplicateChecker {

    private final ParticipationRepository participationRepository;

    public void checkDuplicateParticipation(final Long userSq, final String promotionTitle) {
        participationRepository.checkUserParticipationInTodayPromotion(userSq, promotionTitle)
            .ifPresent(participation -> {
                throw new RuntimeException(PROMOTION_DUPLICATE_PARTICIPATION_PROMOTION);
            });
    }
}
