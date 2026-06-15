package virtusa.project.domains.buyers.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;

import virtusa.project.domains.buyers.dto.WishlistResponse;

public interface WishlistService {

    WishlistResponse addToWishlist(
            Authentication authentication,
            UUID propertyId);

    void removeFromWishlist(
            Authentication authentication,
            UUID propertyId);

    List<WishlistResponse> getWishlist(
            Authentication authentication);

    boolean isWishlisted(
            Authentication authentication,
            UUID propertyId);
}