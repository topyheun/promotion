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
}
