package topy.promotion.modules.promotion.service;

import static topy.promotion.modules.common.Const.PROMOTION_NOT_PROCEEDING_PROMOTION;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.dto.ParticipatePromotionRequest;
import topy.promotion.modules.promotion.dto.ParticipatePromotionResponse;
import topy.promotion.modules.user.domain.User;
import topy.promotion.modules.user.service.UserSearchService;

@Service
@Transactional
@RequiredArgsConstructor
public class PromotionDrawService {

    private final PromotionSearchService promotionSearchService;
    private final WinnerCreateService winnerCreateService;
    private final RewardSearchService rewardSearchService;
    private final UserSearchService userSearchService;
    private final ParticipationCreateService participationCreateService;
    private final ParticipationDuplicateChecker participationDuplicateChecker;

    public ParticipatePromotionResponse draw(final String promotionTitle, ParticipatePromotionRequest participatePromotionRequest) {
        User user = userSearchService.findUserById(participatePromotionRequest.userSq());

        Promotion promotion = promotionSearchService.findPromotionByTitle(promotionTitle);
        if (!promotion.isProceedingPromotion()) {
            throw new RuntimeException(PROMOTION_NOT_PROCEEDING_PROMOTION);
        }
        participationDuplicateChecker.checkDuplicateParticipation(participatePromotionRequest.userSq(), promotionTitle);
        participationCreateService.saveParticipation(user, promotion);

        Reward reward = rewardSearchService.getWinnerReward(promotionTitle);
        if (reward.getRank() == Rank.BOOM) {
            return ParticipatePromotionResponse.of(promotionTitle, reward.getName(), reward.getRank().toString());
        }
        winnerCreateService.createWinner(promotionTitle, user, reward);
        return ParticipatePromotionResponse.of(promotionTitle, reward.getName(), reward.getRank().toString());
    }
}
