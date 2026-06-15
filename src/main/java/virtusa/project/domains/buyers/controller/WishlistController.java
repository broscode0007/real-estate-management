package virtusa.project.domains.buyers.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.buyers.dto.WishlistResponse;
import virtusa.project.domains.buyers.service.WishlistService;

@RestController
@RequestMapping("/api/v1/buyers/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/{propertyId}")
    public ResponseEntity<WishlistResponse> addToWishlist(
            Authentication authentication,
            @PathVariable UUID propertyId) {

        return ResponseEntity.ok(
                wishlistService.addToWishlist(
                        authentication,
                        propertyId));
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> removeFromWishlist(
            Authentication authentication,
            @PathVariable UUID propertyId) {

        wishlistService.removeFromWishlist(
                authentication,
                propertyId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WishlistResponse>> getWishlist(
            Authentication authentication) {

        return ResponseEntity.ok(
                wishlistService.getWishlist(
                        authentication));
    }

    @GetMapping("/{propertyId}/exists")
    public ResponseEntity<Boolean> isWishlisted(
            Authentication authentication,
            @PathVariable UUID propertyId) {

        return ResponseEntity.ok(
                wishlistService.isWishlisted(
                        authentication,
                        propertyId));
    }
}