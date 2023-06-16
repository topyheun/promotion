package topy.promotion.modules.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topy.promotion.modules.promotion.domain.Participation;

import java.time.LocalDate;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    Participation findByUser_IdAndCreatedAt(Long userSq, LocalDate now);
}
