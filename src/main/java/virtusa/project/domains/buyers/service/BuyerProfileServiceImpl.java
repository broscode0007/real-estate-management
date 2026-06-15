package virtusa.project.domains.buyers.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.buyers.dto.BuyerProfilePatchRequest;
import virtusa.project.domains.buyers.dto.BuyerProfileResponse;
import virtusa.project.domains.buyers.dto.BuyerProfileUpdateRequest;
import virtusa.project.domains.buyers.model.Buyer;
import virtusa.project.domains.buyers.repository.BuyerRepository;
import virtusa.project.exceptions.AccountDeletedException;
import virtusa.project.exceptions.ResourceNotFoundException;
import virtusa.project.exceptions.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class BuyerProfileServiceImpl implements BuyerProfileService {

    private final BuyerRepository buyerRepository;

    @Override
    public BuyerProfileResponse getProfile(Authentication authentication) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        return mapToResponse(buyer);
    }

    @Override
    public BuyerProfileResponse updateProfile(
            Authentication authentication,
            BuyerProfileUpdateRequest request) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        buyer.setFirstName(request.getFirstName());
        buyer.setLastName(request.getLastName());
        buyer.setFullName(request.getFullName());

        buyer.setGender(request.getGender());
        buyer.setDateOfBirth(request.getDateOfBirth());

        buyer.setNationality(request.getNationality());
        buyer.setLanguage(request.getLanguage());
        buyer.setTimezone(request.getTimezone());

        buyer.setBio(request.getBio());

        buyer.setCity(request.getCity());
        buyer.setState(request.getState());
        buyer.setCountry(request.getCountry());

        buyer.setAddressLine1(request.getAddressLine1());
        buyer.setAddressLine2(request.getAddressLine2());
        buyer.setPostalCode(request.getPostalCode());

        buyer.setLatitude(request.getLatitude());
        buyer.setLongitude(request.getLongitude());

        buyer.setPreferredPropertyType(
                request.getPreferredPropertyType());

        buyer.setPreferredListingType(
                request.getPreferredListingType());

        buyer.setPreferredCity(
                request.getPreferredCity());

        buyer.setPreferredState(
                request.getPreferredState());

        buyer.setPreferredCountry(
                request.getPreferredCountry());

        buyer.setMinBudget(request.getMinBudget());
        buyer.setMaxBudget(request.getMaxBudget());

        buyer.setMinBedrooms(request.getMinBedrooms());
        buyer.setMaxBedrooms(request.getMaxBedrooms());

        buyer.setMinBathrooms(request.getMinBathrooms());
        buyer.setMaxBathrooms(request.getMaxBathrooms());

        buyer.setPreferredMoveInDate(
                request.getPreferredMoveInDate());

        buyer.setInvestmentBuyer(
                request.isInvestmentBuyer());

        buyer.setFirstTimeBuyer(
                request.isFirstTimeBuyer());

        buyer.setEmailNotificationsEnabled(
                request.isEmailNotificationsEnabled());

        buyer.setPushNotificationsEnabled(
                request.isPushNotificationsEnabled());

        buyer.setReservationNotificationsEnabled(
                request.isReservationNotificationsEnabled());

        buyer.setMarketingNotificationsEnabled(
                request.isMarketingNotificationsEnabled());

        buyer.setPriceDropNotificationsEnabled(
                request.isPriceDropNotificationsEnabled());

        buyer.setProfileCompleted(true);

        buyer = buyerRepository.save(buyer);

        return mapToResponse(buyer);
    }

    @Override
    public BuyerProfileResponse patchProfile(
            Authentication authentication,
            BuyerProfilePatchRequest request) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        if (request.getFirstName() != null)
            buyer.setFirstName(request.getFirstName());

        if (request.getLastName() != null)
            buyer.setLastName(request.getLastName());

        if (request.getFullName() != null)
            buyer.setFullName(request.getFullName());

        if (request.getGender() != null)
            buyer.setGender(request.getGender());

        if (request.getDateOfBirth() != null)
            buyer.setDateOfBirth(request.getDateOfBirth());

        if (request.getNationality() != null)
            buyer.setNationality(request.getNationality());

        if (request.getLanguage() != null)
            buyer.setLanguage(request.getLanguage());

        if (request.getTimezone() != null)
            buyer.setTimezone(request.getTimezone());

        if (request.getBio() != null)
            buyer.setBio(request.getBio());

        if (request.getCity() != null)
            buyer.setCity(request.getCity());

        if (request.getState() != null)
            buyer.setState(request.getState());

        if (request.getCountry() != null)
            buyer.setCountry(request.getCountry());

        if (request.getAddressLine1() != null)
            buyer.setAddressLine1(request.getAddressLine1());

        if (request.getAddressLine2() != null)
            buyer.setAddressLine2(request.getAddressLine2());

        if (request.getPostalCode() != null)
            buyer.setPostalCode(request.getPostalCode());

        if (request.getLatitude() != null)
            buyer.setLatitude(request.getLatitude());

        if (request.getLongitude() != null)
            buyer.setLongitude(request.getLongitude());

        if (request.getPreferredPropertyType() != null)
            buyer.setPreferredPropertyType(
                    request.getPreferredPropertyType());

        if (request.getPreferredListingType() != null)
            buyer.setPreferredListingType(
                    request.getPreferredListingType());

        if (request.getPreferredCity() != null)
            buyer.setPreferredCity(
                    request.getPreferredCity());

        if (request.getPreferredState() != null)
            buyer.setPreferredState(
                    request.getPreferredState());

        if (request.getPreferredCountry() != null)
            buyer.setPreferredCountry(
                    request.getPreferredCountry());

        if (request.getMinBudget() != null)
            buyer.setMinBudget(request.getMinBudget());

        if (request.getMaxBudget() != null)
            buyer.setMaxBudget(request.getMaxBudget());

        if (request.getMinBedrooms() != null)
            buyer.setMinBedrooms(request.getMinBedrooms());

        if (request.getMaxBedrooms() != null)
            buyer.setMaxBedrooms(request.getMaxBedrooms());

        if (request.getMinBathrooms() != null)
            buyer.setMinBathrooms(request.getMinBathrooms());

        if (request.getMaxBathrooms() != null)
            buyer.setMaxBathrooms(request.getMaxBathrooms());

        if (request.getPreferredMoveInDate() != null)
            buyer.setPreferredMoveInDate(
                    request.getPreferredMoveInDate());

        if (request.getInvestmentBuyer() != null)
            buyer.setInvestmentBuyer(
                    request.getInvestmentBuyer());

        if (request.getFirstTimeBuyer() != null)
            buyer.setFirstTimeBuyer(
                    request.getFirstTimeBuyer());

        if (request.getEmailNotificationsEnabled() != null)
            buyer.setEmailNotificationsEnabled(
                    request.getEmailNotificationsEnabled());

        if (request.getPushNotificationsEnabled() != null)
            buyer.setPushNotificationsEnabled(
                    request.getPushNotificationsEnabled());

        if (request.getReservationNotificationsEnabled() != null)
            buyer.setReservationNotificationsEnabled(
                    request.getReservationNotificationsEnabled());

        if (request.getMarketingNotificationsEnabled() != null)
            buyer.setMarketingNotificationsEnabled(
                    request.getMarketingNotificationsEnabled());

        if (request.getPriceDropNotificationsEnabled() != null)
            buyer.setPriceDropNotificationsEnabled(
                    request.getPriceDropNotificationsEnabled());

        buyer = buyerRepository.save(buyer);

        return mapToResponse(buyer);
    }

    @Override
    public void deleteProfile(Authentication authentication) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        buyer.setActive(false);
        buyer.setDeleted(true);

        buyerRepository.save(buyer);
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

    private BuyerProfileResponse mapToResponse(Buyer buyer) {

        return BuyerProfileResponse.builder()
                .firebaseUid(buyer.getFirebaseUid())
                .email(buyer.getEmail())
                .emailVerified(buyer.isEmailVerified())
                .phoneNumber(buyer.getPhoneNumber())
                .phoneVerified(buyer.isPhoneVerified())
                .displayName(buyer.getDisplayName())
                .profilePictureUrl(buyer.getProfilePictureUrl())
                .fullName(buyer.getFullName())
                .firstName(buyer.getFirstName())
                .lastName(buyer.getLastName())
                .gender(buyer.getGender())
                .dateOfBirth(buyer.getDateOfBirth())
                .nationality(buyer.getNationality())
                .language(buyer.getLanguage())
                .timezone(buyer.getTimezone())
                .bio(buyer.getBio())
                .city(buyer.getCity())
                .state(buyer.getState())
                .country(buyer.getCountry())
                .addressLine1(buyer.getAddressLine1())
                .addressLine2(buyer.getAddressLine2())
                .postalCode(buyer.getPostalCode())
                .latitude(buyer.getLatitude())
                .longitude(buyer.getLongitude())
                .preferredPropertyType(
                        buyer.getPreferredPropertyType())
                .preferredListingType(
                        buyer.getPreferredListingType())
                .preferredCity(
                        buyer.getPreferredCity())
                .preferredState(
                        buyer.getPreferredState())
                .preferredCountry(
                        buyer.getPreferredCountry())
                .minBudget(buyer.getMinBudget())
                .maxBudget(buyer.getMaxBudget())
                .minBedrooms(buyer.getMinBedrooms())
                .maxBedrooms(buyer.getMaxBedrooms())
                .minBathrooms(buyer.getMinBathrooms())
                .maxBathrooms(buyer.getMaxBathrooms())
                .preferredMoveInDate(
                        buyer.getPreferredMoveInDate())
                .investmentBuyer(
                        buyer.isInvestmentBuyer())
                .firstTimeBuyer(
                        buyer.isFirstTimeBuyer())
                .totalFavorites(
                        buyer.getTotalFavorites())
                .totalReservations(
                        buyer.getTotalReservations())
                .totalPropertyViews(
                        buyer.getTotalPropertyViews())
                .completedPurchases(
                        buyer.getCompletedPurchases())
                .completedRentals(
                        buyer.getCompletedRentals())
                .emailNotificationsEnabled(
                        buyer.isEmailNotificationsEnabled())
                .pushNotificationsEnabled(
                        buyer.isPushNotificationsEnabled())
                .reservationNotificationsEnabled(
                        buyer.isReservationNotificationsEnabled())
                .marketingNotificationsEnabled(
                        buyer.isMarketingNotificationsEnabled())
                .priceDropNotificationsEnabled(
                        buyer.isPriceDropNotificationsEnabled())
                .active(buyer.isActive())
                .profileCompleted(
                        buyer.isProfileCompleted())
                .createdAt(buyer.getCreatedAt())
                .updatedAt(buyer.getUpdatedAt())
                .lastActiveAt(buyer.getLastActiveAt())
                .build();
    }
}