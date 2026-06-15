package virtusa.project.domains.buyers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.buyers.dto.BuyerSyncResponse;
import virtusa.project.domains.buyers.service.BuyerAuthService;

@RestController
@RequestMapping("/api/v1/buyers/auth")
@RequiredArgsConstructor
public class BuyerAuthController {

    private final BuyerAuthService buyerAuthService;

    @PostMapping("/sync")
    public ResponseEntity<BuyerSyncResponse> syncBuyer(
            Authentication authentication) {

        BuyerSyncResponse response =
                buyerAuthService.syncBuyer(authentication);

        return ResponseEntity.ok(response);
    }
}