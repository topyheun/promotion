package topy.promotion.modules.promotion;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import topy.promotion.modules.promotion.dto.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping("/promotions")
    public ResponseEntity<RegisterPromotionResponse> registerPromotion(@RequestBody @Valid RegisterPromotionRequest registerPromotionRequest) {
        RegisterPromotionResponse registerPromotionResponse = promotionService.createPromotion(registerPromotionRequest);
        return ResponseEntity.ok().body(registerPromotionResponse);
    }

    @GetMapping("/promotions")
    public ResponseEntity<List<SearchPromotionResponse>> searchPromotions() {
        List<SearchPromotionResponse> searchPromotionResponses = promotionService.getPromotion();
        return ResponseEntity.ok().body(searchPromotionResponses);
    }

    @PostMapping("/promotions/{promotionTitle}/rewards")
    public ResponseEntity<List<RegisterRewardResponse>> registerReward(@PathVariable String promotionTitle,
                                                                       @RequestBody @Valid List<RegisterRewardRequest> registerRewardRequests) {
        List<RegisterRewardResponse> registerRewardResponses = promotionService.createRewards(promotionTitle, registerRewardRequests);
        return ResponseEntity.ok().body(registerRewardResponses);
    }

    @PostMapping("/promotions/{promotionTitle}/participation")
    public ResponseEntity<ParticipatePromotionResponse> participatePromotion(@PathVariable String promotionTitle,
                                                                             @RequestBody @Valid ParticipatePromotionRequest participatePromotionRequest) {
        ParticipatePromotionResponse participatePromotionResponse = promotionService.drawLot(promotionTitle, participatePromotionRequest);
        return ResponseEntity.ok().body(participatePromotionResponse);
    }

    @GetMapping("/promotions/{promotionTitle}/winners")
    public ResponseEntity searchWinners(@PathVariable String promotionTitle) {
        List<SearchWinnerResponse> searchWinnerResponse = promotionService.getWinners(promotionTitle);
        return ResponseEntity.ok().body(searchWinnerResponse);
    }
}
