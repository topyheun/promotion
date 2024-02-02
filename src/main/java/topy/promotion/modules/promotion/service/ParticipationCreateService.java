package topy.promotion.modules.promotion.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import topy.promotion.modules.promotion.domain.Participation;
import topy.promotion.modules.promotion.domain.Promotion;
import topy.promotion.modules.promotion.repository.ParticipationRepository;
import topy.promotion.modules.user.User;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationCreateService {

    private final ParticipationRepository participationRepository;

    public void saveParticipation(User user, Promotion promotion) {
        Participation participation = Participation.builder()
            .user(user)
            .promotion(promotion)
            .build();
        participationRepository.save(participation);
    }
}
