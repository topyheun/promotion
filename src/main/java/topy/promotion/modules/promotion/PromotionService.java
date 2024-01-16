package topy.promotion.modules.promotion;

import static topy.promotion.modules.common.Const.PARTICIPATION_DTO_FAIL_MESSAGE;
import static topy.promotion.modules.common.Const.PROMOTION_DUPLICATE_PARTICIPATION_PROMOTION;
import static topy.promotion.modules.common.Const.PROMOTION_NOTHING_REGISTERED_PROMOTION;
import static topy.promotion.modules.common.Const.PROMOTION_NOT_FOUND_PROMOTION;
import static topy.promotion.modules.common.Const.PROMOTION_NOT_PROCEEDING_PROMOTION;
import static topy.promotion.modules.common.Const.PROMOTION_USED_PROMOTION;
import static topy.promotion.modules.common.Const.PROMOTION_WRONG_PROMOTION_PERIOD;
import static topy.promotion.modules.common.Const.REWARD_DUPLICATE_REWARD;
import static topy.promotion.modules.common.Const.USER_NOT_FOUND_ACCOUNT;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.infra.aop.DistributedLock;
import topy.promotion.modules.promotion.domain.Participation;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.domain.Rank;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.domain.Winner;
import topy.promotion.modules.promotion.dto.ParticipatePromotionRequest;
import topy.promotion.modules.promotion.dto.ParticipatePromotionResponse;
import topy.promotion.modules.promotion.dto.RegisterPromotionRequest;
import topy.promotion.modules.promotion.dto.RegisterPromotionResponse;
import topy.promotion.modules.promotion.dto.RegisterRewardRequest;
import topy.promotion.modules.promotion.dto.RegisterRewardResponse;
import topy.promotion.modules.promotion.dto.SearchPromotionResponse;
import topy.promotion.modules.promotion.dto.SearchWinnerResponse;
import topy.promotion.modules.promotion.repository.ParticipationRepository;
import topy.promotion.modules.promotion.repository.PromotionRepository;
import topy.promotion.modules.promotion.repository.RewardRepository;
import topy.promotion.modules.promotion.repository.WinnerRepository;
import topy.promotion.modules.user.User;
import topy.promotion.modules.user.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final RewardRepository rewardRepository;
    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;
    private final WinnerRepository winnerRepository;

    @Transactional
    public RegisterPromotionResponse createPromotion(RegisterPromotionRequest registerPromotionRequest) {
        if (promotionRepository.existsByTitle(registerPromotionRequest.getTitle())) {
            throw new RuntimeException(PROMOTION_USED_PROMOTION);
        }
        checkStartDateBeforeEndDate(registerPromotionRequest.getStartDate(), registerPromotionRequest.getEndDate());

        Promotion promotion = registerPromotionRequest.toPromotion();
        promotionRepository.save(promotion);

        return RegisterPromotionResponse.of(promotion.getTitle());
    }

    private void checkStartDateBeforeEndDate(final LocalDateTime startDate, final LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new RuntimeException(PROMOTION_WRONG_PROMOTION_PERIOD);
        }
    }

    public List<SearchPromotionResponse> getPromotion() {
        List<Promotion> promotions = promotionRepository.findAll();
        if (promotions.isEmpty()) {
            throw new RuntimeException(PROMOTION_NOTHING_REGISTERED_PROMOTION);
        }
        return promotions.stream()
            .map(SearchPromotionResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<RegisterRewardResponse> createRewards(final String promotionTitle, List<RegisterRewardRequest> registerRewardRequests) {
        Promotion promotion = findPromotionByTitle(promotionTitle);
        checkExistsReward(promotionTitle, registerRewardRequests);

        List<Reward> rewards = registerRewardRequests.stream()
            .map(registerRewardRequest -> registerRewardRequest.toReward(promotion))
            .collect(Collectors.toList());
        rewardRepository.saveAll(rewards);

        List<RegisterRewardResponse> registerRewardResponses = registerRewardRequests.stream()
            .map(RegisterRewardRequest::toResponse)
            .collect(Collectors.toList());
        return registerRewardResponses;
    }

    private void checkExistsReward(final String promotionTitle, List<RegisterRewardRequest> registerRewardRequests) {
        for (final RegisterRewardRequest registerRewardRequest : registerRewardRequests) {
            if (rewardRepository.existsByNameAndPromotion_Title(registerRewardRequest.getName(), promotionTitle)) {
                throw new RuntimeException(REWARD_DUPLICATE_REWARD);
            }
        }
    }

    @DistributedLock(key = "#promotionTitle")
    @Transactional
    public ParticipatePromotionResponse drawLot(final String promotionTitle, ParticipatePromotionRequest participatePromotionRequest) {
        Promotion promotion = findPromotionByTitle(promotionTitle);
        if (!promotion.isProceedingPromotion()) {
            throw new RuntimeException(PROMOTION_NOT_PROCEEDING_PROMOTION);
        }
        validateDuplicateParticipation(participatePromotionRequest.getUserSq(), promotionTitle);

        Reward reward = getReward(promotionTitle);
        User user = findUserById(participatePromotionRequest.getUserSq());

        saveParticipation(user, promotion);
        return participatePromotionResponse(promotionTitle, user, reward);
    }

    private Promotion findPromotionByTitle(String promotionTitle) {
        Promotion promotion = promotionRepository.findByTitle(promotionTitle)
            .orElseThrow(() -> new RuntimeException(PROMOTION_NOT_FOUND_PROMOTION));
        return promotion;
    }

    private void validateDuplicateParticipation(final Long userSq, final String promotionTitle) {
        participationRepository.checkUserParticipationInTodayPromotion(userSq, promotionTitle)
            .ifPresent(participation -> {
                throw new RuntimeException(PROMOTION_DUPLICATE_PARTICIPATION_PROMOTION);
            });
    }

    private void saveParticipation(User user, Promotion promotion) {
        Participation participation = Participation.builder()
            .user(user)
            .promotion(promotion)
            .build();
        participationRepository.save(participation);
    }

    private Reward getReward(final String promotionTitle) {
        SecureRandom secureRandom = new SecureRandom();
        int randomNumber = secureRandom.nextInt(100) + 1;

        Rank rank;
        if (randomNumber <= 10) {
            rank = Rank.FIRST;
        } else if (randomNumber <= 25) {
            rank = Rank.SECOND;
        } else if (randomNumber <= 45) {
            rank = Rank.THIRD;
        } else {
            return null;
        }
        return rewardRepository.getAvailableReward(rank, promotionTitle);
    }

    private User findUserById(final Long userSq) {
        User user = userRepository.findById(userSq)
            .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND_ACCOUNT));
        return user;
    }

    private void createWinner(String promotionTitle, User user, Reward reward) {
        Winner winner = Winner.builder()
            .winnerName(user.getUsername())
            .participatedPromotionTitle(promotionTitle)
            .winnerRank(reward.getRank().toString())
            .winnerReward(reward.getName())
            .build();
        winnerRepository.save(winner);
    }

    private ParticipatePromotionResponse participatePromotionResponse(String promotionTitle, User user, Reward reward) {
        if (reward != null) {
            reward.decreaseQuantity();
            rewardRepository.save(reward);

            createWinner(promotionTitle, user, reward);

            return ParticipatePromotionResponse.of(promotionTitle, reward.getName(), reward.getRank().toString());
        } else {
            return ParticipatePromotionResponse.of(promotionTitle, PARTICIPATION_DTO_FAIL_MESSAGE, PARTICIPATION_DTO_FAIL_MESSAGE);
        }
    }

    public List<SearchWinnerResponse> getWinners(final String promotionTitle) {
        List<Winner> winners = winnerRepository.findAllByParticipatedPromotionTitle(promotionTitle);

        return winners.stream()
            .map(SearchWinnerResponse::toResponse)
            .collect(Collectors.toList());
    }
}
