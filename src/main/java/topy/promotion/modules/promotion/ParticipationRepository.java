package topy.promotion.modules.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topy.promotion.modules.promotion.domain.Participation;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    Optional<Participation> findByUser_IdAndCreatedAtAndPromotion_Title(Long userSq, LocalDate now, String promotionTitle);
}
