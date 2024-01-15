package topy.promotion.modules.promotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import topy.promotion.modules.promotion.domain.Participation;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long>, ParticipationRepositoryCustom {

}
