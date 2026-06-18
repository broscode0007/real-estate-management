package virtusa.project.domains.listings.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import virtusa.project.domains.listings.model.ListingType;
import virtusa.project.domains.listings.model.PropertyType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePropertyRequest {

    // ==================================================
    // BASIC INFORMATION (Required)
    // ==================================================

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private PropertyType propertyType;

    @NotNull
    private ListingType listingType;

    private String projectName;
    private String builderName;
    private String ownerName;


    // ==================================================
    // PRICING (Required)
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
    // LOCATION (Required)
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

    private List<String> imageUrls;
    private List<String> videoUrls;
    private List<String> floorPlanUrls;
    private String virtualTourUrl;


    // ==================================================
    // NEARBY DISTANCES
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
    // RENTAL DETAILS
    // ==================================================

    private Boolean bachelorsAllowed;
    private Boolean familyPreferred;
    private Boolean petsAllowed;
    private Boolean smokingAllowed;
    private Integer leaseDurationMonths;

    private LocalDate availableFrom;


    // ==================================================
    // RESERVATION SETTINGS
    // ==================================================

    private Boolean reservationsEnabled;
    private Boolean immediateReservationEnabled;
    private Boolean normalReservationEnabled;
    private Boolean slowReservationEnabled;
    private Integer maxNormalReservations;
}