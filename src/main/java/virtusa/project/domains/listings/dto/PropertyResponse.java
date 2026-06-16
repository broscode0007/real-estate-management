package virtusa.project.domains.listings.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import virtusa.project.domains.listings.model.ListingType;
import virtusa.project.domains.listings.model.PropertyStatus;
import virtusa.project.domains.listings.model.PropertyType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyResponse {

    private UUID id;

    private String propertyCode;

    private String title;

    private String description;

    private PropertyType propertyType;

    private ListingType listingType;

    private PropertyStatus status;

    private BigDecimal startPrice;

    private BigDecimal expectedPrice;

    private BigDecimal maxPrice;

    private String locality;

    private String city;

    private String state;

    private String country;

    private Double latitude;

    private Double longitude;

    private Integer bedrooms;

    private Integer bathrooms;

    private Integer parkingSpaces;

    private Double builtUpAreaSqFt;

    private Double averageRating;

    private Long totalReviews;

    private Long viewCount;

    private Long favoriteCount;

    private Boolean verified;

    private Boolean featured;

    private List<String> imageUrls;

    private LocalDateTime createdAt;

    private String address;
    private String landmark;
    private String district;
    private String postalCode;
    private String googleMapsUrl;
    private BigDecimal maintenanceCharge;
    private BigDecimal securityDeposit;
    private BigDecimal monthlyRent;
    private BigDecimal pricePerSqFt;
    private Boolean priceNegotiable;
    private Double plotAreaSqFt;
    private Double carpetAreaSqFt;
    private Double superBuiltUpAreaSqFt;
    private Integer balconies;
    private Integer halls;
    private Integer kitchens;
    private Integer floorNumber;
    private Integer totalFloors;
    private Integer ageOfProperty;
    private Integer yearBuilt;
    private String projectName;
    private String builderName;
    private String ownerName;
    private String facingDirection;
    private Boolean cornerProperty;
    private Boolean vastuCompliant;
    private Boolean furnished;
    private Boolean semiFurnished;
    private Boolean unfurnished;
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
    private Boolean conferenceRoom;
    private Boolean cafeteria;
    private Boolean receptionArea;
    private Boolean centralAirConditioning;
    private Boolean waterSupply;
    private Boolean sewageConnection;
    private Boolean electricityConnection;
    private Boolean internetAvailable;
    private Boolean gasPipeline;
    private List<String> videoUrls;
    private List<String> floorPlanUrls;
    private String virtualTourUrl;
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
    private Boolean approvedByAuthority;
    private Boolean titleClear;
    private Boolean bankApproved;
    private Boolean reraApproved;
    private String reraNumber;
    private Boolean bachelorsAllowed;
    private Boolean familyPreferred;
    private Boolean petsAllowed;
    private Boolean smokingAllowed;
    private Integer leaseDurationMonths;
    private boolean reservationsEnabled;
    private boolean immediateReservationEnabled;
    private boolean normalReservationEnabled;
    private boolean slowReservationEnabled;
    private int maxNormalReservations;
    private Long enquiryCount;
    private LocalDateTime listedAt;
    private LocalDateTime updatedAt;
    private LocalDate availableFrom;
}