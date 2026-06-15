package virtusa.project.domains.buyers.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.buyers.dto.WishlistResponse;
import virtusa.project.domains.buyers.model.Buyer;
import virtusa.project.domains.buyers.model.Wishlist;
import virtusa.project.domains.buyers.repository.BuyerRepository;
import virtusa.project.domains.buyers.repository.WishlistRepository;
import virtusa.project.exceptions.AccountDeletedException;
import virtusa.project.exceptions.BadRequestException;
import virtusa.project.exceptions.ResourceNotFoundException;
import virtusa.project.exceptions.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final BuyerRepository buyerRepository;

    @Override
    public WishlistResponse addToWishlist(
            Authentication authentication,
            UUID propertyId) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        if (wishlistRepository.existsByBuyerFirebaseUidAndPropertyId(
                buyer.getFirebaseUid(),
                propertyId)) {

            throw new BadRequestException(
                    "Property already exists in wishlist");
        }

        Wishlist wishlist = Wishlist.builder()
                .buyerFirebaseUid(buyer.getFirebaseUid())
                .propertyId(propertyId)
                .build();

        wishlist = wishlistRepository.save(wishlist);

        return mapToResponse(wishlist);
    }

    @Override
    public void removeFromWishlist(
            Authentication authentication,
            UUID propertyId) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        Wishlist wishlist = wishlistRepository
                .findByBuyerFirebaseUidAndPropertyId(
                        buyer.getFirebaseUid(),
                        propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wishlist item not found"));

        wishlistRepository.delete(wishlist);
    }

    @Override
    public List<WishlistResponse> getWishlist(
            Authentication authentication) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        return wishlistRepository
                .findByBuyerFirebaseUidOrderByCreatedAtDesc(
                        buyer.getFirebaseUid())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public boolean isWishlisted(
            Authentication authentication,
            UUID propertyId) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        return wishlistRepository
                .existsByBuyerFirebaseUidAndPropertyId(
                        buyer.getFirebaseUid(),
                        propertyId);
    }

    private Buyer getAuthenticatedBuyer(
            Authentication authentication) {

        if (authentication == null ||
                authentication.getName() == null) {

            throw new UnauthorizedException(
                    "Authentication required");
        }

        String firebaseUid = authentication.getName();

        Buyer buyer = buyerRepository
                .findByFirebaseUid(firebaseUid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Buyer not found"));

        if (buyer.isAccountDeleted()) {
            throw new AccountDeletedException();
        }

        return buyer;
    }

    private WishlistResponse mapToResponse(
            Wishlist wishlist) {

        return WishlistResponse.builder()
                .id(wishlist.getId())
                .propertyId(wishlist.getPropertyId())
                .createdAt(wishlist.getCreatedAt())
                .build();
    }
}