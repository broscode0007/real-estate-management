package virtusa.project.domains.agent.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agent {

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

    private String tagline;

    // Professional Details

    @Column(name = "agency_name")
    private String agencyName;

    @Column(name = "agent_license_number", unique = true)
    private String agentLicenseNumber;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    private String specialization;

    private String designation;

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

    // Performance Metrics

    @Builder.Default
    @Column(name = "average_rating")
    private Double averageRating = 0.0;

    @Builder.Default
    @Column(name = "total_reviews")
    private Integer totalReviews = 0;

    @Builder.Default
    @Column(name = "active_listings")
    private Integer activeListings = 0;

    @Builder.Default
    @Column(name = "total_listings")
    private Integer totalListings = 0;

    @Builder.Default
    @Column(name = "sold_properties")
    private Integer soldProperties = 0;

    @Builder.Default
    @Column(name = "rented_properties")
    private Integer rentedProperties = 0;

    @Builder.Default
    @Column(name = "total_leads")
    private Integer totalLeads = 0;

    // Verification

    @Builder.Default
    @Column(name = "kyc_verified")
    private boolean kycVerified = false;

    @Builder.Default
    @Column(name = "license_verified")
    private boolean licenseVerified = false;

    @Builder.Default
    @Column(name = "background_verified")
    private boolean backgroundVerified = false;

    // Notification Settings

    @Builder.Default
    @Column(name = "email_notifications_enabled")
    private boolean emailNotificationsEnabled = true;

    @Builder.Default
    @Column(name = "push_notifications_enabled")
    private boolean pushNotificationsEnabled = true;

    @Builder.Default
    @Column(name = "chat_notifications_enabled")
    private boolean chatNotificationsEnabled = true;

    @Builder.Default
    @Column(name = "marketing_notifications_enabled")
    private boolean marketingNotificationsEnabled = false;

    // Status

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AgentStatus status = AgentStatus.PENDING;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

    @Builder.Default
    @Column(nullable = false)
    private boolean deleted = false;

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
}