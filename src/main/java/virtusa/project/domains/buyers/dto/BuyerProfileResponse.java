package virtusa.project.domains.buyers.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BuyerProfileResponse {

    private final String firebaseUid;

    private final String email;

    private final boolean emailVerified;

    private final String phoneNumber;

    private final boolean phoneVerified;

    private final String displayName;

    private final String profilePictureUrl;

    private final String fullName;

    private final String firstName;

    private final String lastName;

    private final String gender;

    private final LocalDate dateOfBirth;

    private final String nationality;

    private final String language;

    private final String timezone;

    private final String bio;

    private final String city;

    private final String state;

    private final String country;

    private final String addressLine1;

    private final String addressLine2;

    private final String postalCode;

    private final Double latitude;

    private final Double longitude;

    private final String preferredPropertyType;

    private final String preferredListingType;

    private final String preferredCity;

    private final String preferredState;

    private final String preferredCountry;

    private final Double minBudget;

    private final Double maxBudget;

    private final Integer minBedrooms;

    private final Integer maxBedrooms;

    private final Integer minBathrooms;

    private final Integer maxBathrooms;

    private final LocalDate preferredMoveInDate;

    private final boolean investmentBuyer;

    private final boolean firstTimeBuyer;

    private final Integer totalFavorites;

    private final Integer totalReservations;

    private final Integer totalPropertyViews;

    private final Integer completedPurchases;

    private final Integer completedRentals;

    private final boolean emailNotificationsEnabled;

    private final boolean pushNotificationsEnabled;

    private final boolean reservationNotificationsEnabled;

    private final boolean marketingNotificationsEnabled;

    private final boolean priceDropNotificationsEnabled;

    private final boolean active;

    private final boolean profileCompleted;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    private final LocalDateTime lastActiveAt;
}