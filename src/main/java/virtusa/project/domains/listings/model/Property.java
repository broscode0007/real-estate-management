package virtusa.project.domains.listings.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import virtusa.project.domains.agent.model.Agent;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    // ==================================================
    // PRIMARY
    // ==================================================

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String propertyCode;

    // ==================================================
    // BASIC INFORMATION
    // ==================================================
    @NotBlank
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ListingType listingType;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    private String projectName;

    private String builderName;

    private String ownerName;

    @Builder.Default
    private Boolean verified = false;

    @Builder.Default
    private Boolean featured = false;

    @Builder.Default
    private Boolean premiumListing = false;

    // ==================================================
    // PRICING
    // ==================================================
    @NotNull
    private BigDecimal startPrice;
    @NotNull
    private BigDecimal expectedPrice;
    @NotNull
    private BigDecimal maxPrice;

    private BigDecimal maintenanceCharge;

    private BigDecimal securityDeposit;

    private BigDecimal monthlyRent;

    private BigDecimal pricePerSqFt;

    private Boolean priceNegotiable;

    // ==================================================
    // LOCATION
    // ==================================================
    @NotBlank
    private String address;

    private String landmark;

    private String locality;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    @NotBlank
    private String postalCode;

    private Double latitude;

    private Double longitude;

    @Column(length = 1000)
    private String googleMapsUrl;

    // ==================================================
    // AREA DETAILS
    // ==================================================

    private Double plotAreaSqFt;

    private Double builtUpAreaSqFt;

    private Double carpetAreaSqFt;

    private Double superBuiltUpAreaSqFt;

    // ==================================================
    // PROPERTY DETAILS
    // ==================================================

    private Integer bedrooms;

    private Integer bathrooms;

    private Integer balconies;

    private Integer halls;

    private Integer kitchens;

    private Integer parkingSpaces;

    private Integer floorNumber;

    private Integer totalFloors;

    private Integer ageOfProperty;

    private Integer yearBuilt;

    // ==================================================
    // ORIENTATION
    // ==================================================

    private String facingDirection;

    private Boolean cornerProperty;

    private Boolean vastuCompliant;

    // ==================================================
    // FURNISHING
    // ==================================================

    private Boolean furnished;

    private Boolean semiFurnished;

    private Boolean unfurnished;

    // ==================================================
    // AMENITIES
    // ==================================================

    private Boolean lift;

    private Boolean powerBackup;

    private Boolean security24x7;

    private Boolean cctv;

    private Boolean gatedCommunity;

    private Boolean swimmingPool;

    private Boolean gym;

    private Boolean clubhouse;

    private Boolean childrensPlayArea;

    private Boolean joggingTrack;

    private Boolean indoorGames;

    private Boolean outdoorSports;

    private Boolean park;

    private Boolean visitorParking;

    private Boolean rainWaterHarvesting;

    private Boolean fireSafety;

    private Boolean intercom;

    private Boolean wifiReady;

    // ==================================================
    // COMMERCIAL FEATURES
    // ==================================================

    private Boolean conferenceRoom;

    private Boolean cafeteria;

    private Boolean receptionArea;

    private Boolean centralAirConditioning;

    // ==================================================
    // UTILITIES
    // ==================================================

    private Boolean waterSupply;

    private Boolean sewageConnection;

    private Boolean electricityConnection;

    private Boolean internetAvailable;

    private Boolean gasPipeline;

    // ==================================================
    // MEDIA
    // ==================================================

    @ElementCollection
    private List<String> imageUrls;

    @ElementCollection
    private List<String> videoUrls;

    @ElementCollection
    private List<String> floorPlanUrls;

    private String virtualTourUrl;

    // ==================================================
    // NEARBY PLACES DISTANCES (KM)
    // ==================================================

    private Double nearestSchoolDistance;

    private Double nearestCollegeDistance;

    private Double nearestHospitalDistance;

    private Double nearestMetroDistance;

    private Double nearestBusStandDistance;

    private Double nearestRailwayStationDistance;

    private Double nearestAirportDistance;

    private Double nearestMallDistance;

    private Double nearestMarketDistance;

    private Double nearestParkDistance;

    // ==================================================
    // LEGAL DETAILS
    // ==================================================

    private Boolean approvedByAuthority;

    private Boolean titleClear;

    private Boolean bankApproved;

    private Boolean reraApproved;

    private String reraNumber;

    // ==================================================
    // RENTAL SPECIFIC
    // ==================================================

    private Boolean bachelorsAllowed;

    private Boolean familyPreferred;

    private Boolean petsAllowed;

    private Boolean smokingAllowed;

    private Integer leaseDurationMonths;

    // ==================================================
    // SEO / SEARCH
    // ==================================================

    @Builder.Default
    private Long viewCount = 0L;

    @Builder.Default
    private Long favoriteCount = 0L;

    @Builder.Default
    private Long enquiryCount = 0L;

    @Builder.Default
    private Double averageRating = 0.0;

    // ==================================================
    // RELATIONSHIPS
    // ==================================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    // ==================================================
    // AUDIT
    // ==================================================

    private LocalDate availableFrom;

    private LocalDateTime listedAt;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;
    
    @Builder.Default
    @Column(nullable = false)
    private Boolean reservationsEnabled = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean immediateReservationEnabled = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean normalReservationEnabled = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean slowReservationEnabled = false;

    @Builder.Default
    private Integer maxNormalReservations = 0;

    @Column(nullable = false)
    @Builder.Default
    private Boolean deleted = false;
}

