package topy.promotion.modules.promotion.service;

import java.security.SecureRandom;
import org.springframework.stereotype.Service;
import topy.promotion.modules.promotion.domain.Rank;

@Service
public class RewardCreateRankService {

    public Rank createRank() {
        SecureRandom secureRandom = new SecureRandom();
        int randomNumber = secureRandom.nextInt(100) + 1;

        Rank rank;
        if (randomNumber <= 30) {
            rank = Rank.WIN;
        } else {
            rank = Rank.BOOM;
        }
        return rank;
    }
}
