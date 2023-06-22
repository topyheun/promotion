package topy.promotion.modules.promotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topy.promotion.modules.promotion.domain.Reward;

import java.util.Optional;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long>, RewardRepositoryCustom {

    boolean existsByNameAndPromotion_Title(String name, String promotionTitle);

    Optional<Reward> findByName(String rewardName);
}
