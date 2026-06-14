package virtusa.project.domains.listings.dto;

import java.math.BigDecimal;

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

    // Basic Information
    private String title;
    private String description;

    private PropertyType propertyType;
    private ListingType listingType;

    // Pricing
    private BigDecimal startPrice;
    private BigDecimal expectedPrice;
    private BigDecimal maxPrice;

    // Location
    private String address;
    private String locality;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    private Double latitude;
    private Double longitude;

    // Area
    private Double builtUpAreaSqFt;
    private Double carpetAreaSqFt;

    // Details
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer balconies;
    private Integer parkingSpaces;

    // Reservation Settings
    private Boolean reservationsEnabled;
    private Boolean immediateReservationEnabled;
    private Boolean normalReservationEnabled;
    private Boolean slowReservationEnabled;
    private Integer maxNormalReservations;
}