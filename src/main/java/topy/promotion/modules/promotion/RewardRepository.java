package topy.promotion.modules.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topy.promotion.modules.promotion.domain.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
}
