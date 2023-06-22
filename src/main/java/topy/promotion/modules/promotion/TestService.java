package topy.promotion.modules.promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import topy.promotion.infra.aop.DistributedLock;
import topy.promotion.modules.promotion.domain.Reward;
import topy.promotion.modules.promotion.repository.RewardRepository;

@Component
@RequiredArgsConstructor
public class TestService {

    private final RewardRepository rewardRepository;

    @Transactional
    @DistributedLock(key = "#lockName")
    public void rewardDecrease(String lockName, String rewardName) {
        Reward reward = rewardRepository.findByName(rewardName)
                .orElseThrow(IllegalAccessError::new);

        reward.decreaseQuantity();
    }
}
