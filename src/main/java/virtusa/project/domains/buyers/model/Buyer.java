package virtusa.project.domains.buyers.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "buyers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Buyer {

    @Id
    @Column(name = "firebase_uid", nullable = false, unique = true)
    private String firebaseUid;

    // Firebase Data

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "email_verified", nullable = false)
    @Builder.Default
    private boolean emailVerified = false;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_verified", nullable = false)
    @Builder.Default
    private boolean phoneVerified = false;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "firebase_created_at")
    private LocalDateTime firebaseCreatedAt;

    @Column(name = "firebase_last_sign_in_at")
    private LocalDateTime firebaseLastSignInAt;

    // Personal Details

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String nationality;

    private String language;

    private String timezone;

    @Column(length = 1000)
    private String bio;

    // Location

    private String city;

    private String state;

    private String country;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "postal_code")
    private String postalCode;

    private Double latitude;

    private Double longitude;

    // Buyer Preferences

    @Column(name = "preferred_property_type")
    private String preferredPropertyType;

    @Column(name = "preferred_listing_type")
    private String preferredListingType;

    @Column(name = "preferred_city")
    private String preferredCity;

    @Column(name = "preferred_state")
    private String preferredState;

    @Column(name = "preferred_country")
    private String preferredCountry;

    @Column(name = "min_budget")
    private Double minBudget;

    @Column(name = "max_budget")
    private Double maxBudget;

    @Column(name = "min_bedrooms")
    private Integer minBedrooms;

    @Column(name = "max_bedrooms")
    private Integer maxBedrooms;

    @Column(name = "min_bathrooms")
    private Integer minBathrooms;

    @Column(name = "max_bathrooms")
    private Integer maxBathrooms;

    @Column(name = "preferred_move_in_date")
    private LocalDate preferredMoveInDate;

    @Column(name = "investment_buyer")
    @Builder.Default
    private boolean investmentBuyer = false;

    @Column(name = "first_time_buyer")
    @Builder.Default
    private boolean firstTimeBuyer = false;

    // Buyer Activity Metrics

    @Builder.Default
    @Column(name = "total_favorites")
    private Integer totalFavorites = 0;

    @Builder.Default
    @Column(name = "total_reservations")
    private Integer totalReservations = 0;

    @Builder.Default
    @Column(name = "total_property_views")
    private Integer totalPropertyViews = 0;

    @Builder.Default
    @Column(name = "completed_purchases")
    private Integer completedPurchases = 0;

    @Builder.Default
    @Column(name = "completed_rentals")
    private Integer completedRentals = 0;

    // Notification Settings

    @Builder.Default
    @Column(name = "email_notifications_enabled")
    private boolean emailNotificationsEnabled = true;

    @Builder.Default
    @Column(name = "push_notifications_enabled")
    private boolean pushNotificationsEnabled = true;

    @Builder.Default
    @Column(name = "reservation_notifications_enabled")
    private boolean reservationNotificationsEnabled = true;

    @Builder.Default
    @Column(name = "marketing_notifications_enabled")
    private boolean marketingNotificationsEnabled = false;

    @Builder.Default
    @Column(name = "price_drop_notifications_enabled")
    private boolean priceDropNotificationsEnabled = true;

    // Status

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

    @Builder.Default
    @Column(nullable = false)
    private boolean deleted = false;

    @Builder.Default
    @Column(name = "profile_completed")
    private boolean profileCompleted = false;

    // Audit

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "last_active_at")
    private LocalDateTime lastActiveAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isAccountDeleted() {
        return !this.active && this.deleted;
    }
}