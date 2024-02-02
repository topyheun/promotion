package topy.promotion.modules.promotion.service;

import static topy.promotion.modules.common.Const.PARTICIPATION_DTO_FAIL_MESSAGE;
import static topy.promotion.modules.common.Const.PROMOTION_NOT_PROCEEDING_PROMOTION;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.infra.aop.DistributedLock;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.dto.ParticipatePromotionRequest;
import topy.promotion.modules.promotion.dto.ParticipatePromotionResponse;
import topy.promotion.modules.user.User;
import topy.promotion.modules.user.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class PromotionDrawService {

    private final PromotionSearchService promotionSearchService;
    private final WinnerCreateService winnerCreateService;
    private final RewardSearchService rewardSearchService;
    private final UserService userService;
    private final ParticipationCreateService participationCreateService;
    private final ParticipationDuplicateCheckService participationDuplicateCheckService;

    @DistributedLock(key = "#promotionTitle")
    public ParticipatePromotionResponse draw(final String promotionTitle, ParticipatePromotionRequest participatePromotionRequest) {

        Promotion promotion = promotionSearchService.findPromotionByTitle(promotionTitle);
        if (!promotion.isProceedingPromotion()) {
            throw new RuntimeException(PROMOTION_NOT_PROCEEDING_PROMOTION);
        }
        participationDuplicateCheckService.checkDuplicateParticipation(participatePromotionRequest.userSq(), promotionTitle);

        Reward reward = rewardSearchService.getReward(promotionTitle);

        if (reward == null) {
            return ParticipatePromotionResponse.of(promotionTitle, PARTICIPATION_DTO_FAIL_MESSAGE, PARTICIPATION_DTO_FAIL_MESSAGE);
        }
        reward.decreaseQuantity();

        User user = userService.findUserById(participatePromotionRequest.userSq());
        participationCreateService.saveParticipation(user, promotion);

        winnerCreateService.createWinner(promotionTitle, user, reward);
        return ParticipatePromotionResponse.of(promotionTitle, reward.getName(), reward.getRank().toString());
    }
}
