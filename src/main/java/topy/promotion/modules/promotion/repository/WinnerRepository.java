package topy.promotion.modules.promotion.repository;

import java.util.List;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topy.promotion.modules.promotion.domain.Winner;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Log> {

    List<Winner> findAllByParticipatedPromotionTitle(String promotionTitle);
}
