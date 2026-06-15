package virtusa.project.domains.buyers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.buyers.dto.BuyerProfilePatchRequest;
import virtusa.project.domains.buyers.dto.BuyerProfileResponse;
import virtusa.project.domains.buyers.dto.BuyerProfileUpdateRequest;
import virtusa.project.domains.buyers.service.BuyerProfileService;

@RestController
@RequestMapping("/api/v1/buyers/profile")
@RequiredArgsConstructor
public class BuyerProfileController {

    private final BuyerProfileService buyerProfileService;

    @GetMapping
    public ResponseEntity<BuyerProfileResponse> getProfile(
            Authentication authentication) {

        return ResponseEntity.ok(
                buyerProfileService.getProfile(authentication));
    }

    @PutMapping
    public ResponseEntity<BuyerProfileResponse> updateProfile(
            Authentication authentication,
            @RequestBody BuyerProfileUpdateRequest request) {

        return ResponseEntity.ok(
                buyerProfileService.updateProfile(
                        authentication,
                        request));
    }

    @PatchMapping
    public ResponseEntity<BuyerProfileResponse> patchProfile(
            Authentication authentication,
            @RequestBody BuyerProfilePatchRequest request) {

        return ResponseEntity.ok(
                buyerProfileService.patchProfile(
                        authentication,
                        request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProfile(
            Authentication authentication) {

        buyerProfileService.deleteProfile(authentication);

        return ResponseEntity.noContent().build();
    }
}