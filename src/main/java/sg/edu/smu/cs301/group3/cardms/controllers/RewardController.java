package sg.edu.smu.cs301.group3.cardms.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;
import sg.edu.smu.cs301.group3.cardms.services.RewardService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/card/rewards")
public class RewardController {

    private final RewardService rewardService;

    @GetMapping("/{tenant}/{customerId}")
    public ResponseEntity<List<RewardDto>> getCustomerRewards(@PathVariable("tenant") String tenant, @PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(rewardService.getCustomerEarnedRewards(tenant, customerId));
    }

    @GetMapping("/{tenant}/{customerId}/{cardType}")
    public ResponseEntity<List<RewardDto>> getCustomerRewardsByCard(@PathVariable("tenant") String tenant, @PathVariable("customerId") String customerId, @PathVariable("cardType") String cardType) {
        return ResponseEntity.ok(rewardService.getCardEarnedRewards(tenant, customerId, cardType));
    }

}
