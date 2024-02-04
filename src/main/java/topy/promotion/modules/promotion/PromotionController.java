package topy.promotion.modules.promotion;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import topy.promotion.modules.promotion.dto.ParticipatePromotionRequest;
import topy.promotion.modules.promotion.dto.ParticipatePromotionResponse;
import topy.promotion.modules.promotion.dto.RegisterPromotionRequest;
import topy.promotion.modules.promotion.dto.RegisterPromotionResponse;
import topy.promotion.modules.promotion.dto.RegisterRewardRequest;
import topy.promotion.modules.promotion.dto.RegisterRewardResponse;
import topy.promotion.modules.promotion.dto.SearchPromotionResponse;
import topy.promotion.modules.promotion.dto.SearchWinnerResponse;
import topy.promotion.modules.promotion.service.PromotionCreateService;
import topy.promotion.modules.promotion.service.PromotionDrawService;
import topy.promotion.modules.promotion.service.PromotionSearchService;
import topy.promotion.modules.promotion.service.RewardCreateService;
import topy.promotion.modules.promotion.service.WinnerSearchService;

@RestController
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionCreateService promotionCreateService;
    private final PromotionSearchService promotionSearchService;
    private final RewardCreateService rewardCreateService;
    private final PromotionDrawService promotionDrawService;
    private final WinnerSearchService winnerSearchService;

    @PostMapping("/promotions")
    public ResponseEntity<RegisterPromotionResponse> registerPromotion(@RequestBody @Valid RegisterPromotionRequest registerPromotionRequest) {
        RegisterPromotionResponse registerPromotionResponse = promotionCreateService.createPromotion(registerPromotionRequest);
        return ResponseEntity.ok().body(registerPromotionResponse);
    }

    @GetMapping("/promotions")
    public ResponseEntity<List<SearchPromotionResponse>> searchPromotions() {
        List<SearchPromotionResponse> searchPromotionResponses = promotionSearchService.getPromotion();
        return ResponseEntity.ok().body(searchPromotionResponses);
    }

    @PostMapping("/promotions/{promotionTitle}/rewards")
    public ResponseEntity<RegisterRewardResponse> registerReward(@PathVariable String promotionTitle, @RequestBody @Valid RegisterRewardRequest registerRewardRequest) {
        RegisterRewardResponse registerRewardResponses = rewardCreateService.createRewards(promotionTitle, registerRewardRequest);
        return ResponseEntity.ok().body(registerRewardResponses);
    }

    @PostMapping("/promotions/{promotionTitle}/participation")
    public ResponseEntity<ParticipatePromotionResponse> participatePromotion(@PathVariable String promotionTitle, @RequestBody @Valid ParticipatePromotionRequest participatePromotionRequest) {
        ParticipatePromotionResponse participatePromotionResponse = promotionDrawService.draw(promotionTitle, participatePromotionRequest);
        return ResponseEntity.ok().body(participatePromotionResponse);
    }

    @GetMapping("/promotions/{promotionTitle}/winners")
    public ResponseEntity searchWinners(@PathVariable String promotionTitle) {
        List<SearchWinnerResponse> searchWinnerResponse = winnerSearchService.getWinners(promotionTitle);
        return ResponseEntity.ok().body(searchWinnerResponse);
    }
}
