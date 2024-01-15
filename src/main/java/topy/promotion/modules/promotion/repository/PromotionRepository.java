package topy.promotion.modules.promotion.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topy.promotion.modules.promotion.domain.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    boolean existsByTitle(String promotionTitle);

    Optional<Promotion> findByTitle(String promotionTitle);
}