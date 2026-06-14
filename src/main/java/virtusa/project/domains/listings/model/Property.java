// package virtusa.project.domains.listings.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.math.BigDecimal;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;

// @Entity
// @Table(name = "properties")
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Property {

//     // ==================================================
//     // PRIMARY
//     // ==================================================

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String propertyCode;

//     // ==================================================
//     // BASIC INFORMATION
//     // ==================================================

//     private String title;

//     @Column(columnDefinition = "TEXT")
//     private String description;

//     @Enumerated(EnumType.STRING)
//     private PropertyType propertyType;

//     @Enumerated(EnumType.STRING)
//     private ListingType listingType;

//     @Enumerated(EnumType.STRING)
//     private PropertyStatus status;

//     private String projectName;

//     private String builderName;

//     private String ownerName;

//     private Boolean verified;

//     private Boolean featured;

//     private Boolean premiumListing;

//     // ==================================================
//     // PRICING
//     // ==================================================

//     private BigDecimal startPrice;

//     private BigDecimal expectedPrice;

//     private BigDecimal maxPrice;

//     private BigDecimal maintenanceCharge;

//     private BigDecimal securityDeposit;

//     private BigDecimal monthlyRent;

//     private BigDecimal pricePerSqFt;

//     private Boolean priceNegotiable;

//     // ==================================================
//     // LOCATION
//     // ==================================================

//     private String address;

//     private String landmark;

//     private String locality;

//     private String city;

//     private String district;

//     private String state;

//     private String country;

//     private String postalCode;

//     private Double latitude;

//     private Double longitude;

//     // ==================================================
//     // AREA DETAILS
//     // ==================================================

//     private Double plotAreaSqFt;

//     private Double builtUpAreaSqFt;

//     private Double carpetAreaSqFt;

//     private Double superBuiltUpAreaSqFt;

//     // ==================================================
//     // PROPERTY DETAILS
//     // ==================================================

//     private Integer bedrooms;

//     private Integer bathrooms;

//     private Integer balconies;

//     private Integer halls;

//     private Integer kitchens;

//     private Integer parkingSpaces;

//     private Integer floorNumber;

//     private Integer totalFloors;

//     private Integer ageOfProperty;

//     private Integer yearBuilt;

//     // ==================================================
//     // ORIENTATION
//     // ==================================================

//     private String facingDirection;

//     private Boolean cornerProperty;

//     private Boolean vastuCompliant;

//     // ==================================================
//     // FURNISHING
//     // ==================================================

//     private Boolean furnished;

//     private Boolean semiFurnished;

//     private Boolean unfurnished;

//     // ==================================================
//     // AMENITIES
//     // ==================================================

//     private Boolean lift;

//     private Boolean powerBackup;

//     private Boolean security24x7;

//     private Boolean cctv;

//     private Boolean gatedCommunity;

//     private Boolean swimmingPool;

//     private Boolean gym;

//     private Boolean clubhouse;

//     private Boolean children'sPlayArea;

//     private Boolean joggingTrack;

//     private Boolean indoorGames;

//     private Boolean outdoorSports;

//     private Boolean park;

//     private Boolean visitorParking;

//     private Boolean rainWaterHarvesting;

//     private Boolean fireSafety;

//     private Boolean intercom;

//     private Boolean wifiReady;

//     // ==================================================
//     // COMMERCIAL FEATURES
//     // ==================================================

//     private Boolean conferenceRoom;

//     private Boolean cafeteria;

//     private Boolean receptionArea;

//     private Boolean centralAirConditioning;

//     // ==================================================
//     // UTILITIES
//     // ==================================================

//     private Boolean waterSupply;

//     private Boolean sewageConnection;

//     private Boolean electricityConnection;

//     private Boolean internetAvailable;

//     private Boolean gasPipeline;

//     // ==================================================
//     // MEDIA
//     // ==================================================

//     @ElementCollection
//     private List<String> imageUrls;

//     @ElementCollection
//     private List<String> videoUrls;

//     @ElementCollection
//     private List<String> floorPlanUrls;

//     private String virtualTourUrl;

//     // ==================================================
//     // NEARBY PLACES DISTANCES (KM)
//     // ==================================================

//     private Double nearestSchoolDistance;

//     private Double nearestCollegeDistance;

//     private Double nearestHospitalDistance;

//     private Double nearestMetroDistance;

//     private Double nearestBusStandDistance;

//     private Double nearestRailwayStationDistance;

//     private Double nearestAirportDistance;

//     private Double nearestMallDistance;

//     private Double nearestMarketDistance;

//     private Double nearestParkDistance;

//     // ==================================================
//     // LEGAL DETAILS
//     // ==================================================

//     private Boolean approvedByAuthority;

//     private Boolean titleClear;

//     private Boolean bankApproved;

//     private Boolean reraApproved;

//     private String reraNumber;

//     // ==================================================
//     // RENTAL SPECIFIC
//     // ==================================================

//     private Boolean bachelorsAllowed;

//     private Boolean familyPreferred;

//     private Boolean petsAllowed;

//     private Boolean smokingAllowed;

//     private Integer leaseDurationMonths;

//     // ==================================================
//     // SEO / SEARCH
//     // ==================================================

//     private Long viewCount;

//     private Long favoriteCount;

//     private Long enquiryCount;

//     private Double averageRating;

//     // ==================================================
//     // RELATIONSHIPS
//     // ==================================================

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "agent_id")
//     private Agent agent;

//     // ==================================================
//     // AUDIT
//     // ==================================================

//     private LocalDate availableFrom;

//     private LocalDateTime listedAt;

//     private LocalDateTime updatedAt;

//     private LocalDateTime createdAt;
// }