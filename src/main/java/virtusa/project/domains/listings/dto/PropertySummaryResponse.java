package virtusa.project.domains.listings.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import virtusa.project.domains.listings.model.ListingType;
import virtusa.project.domains.listings.model.PropertyType;


@Getter
@Setter
@Builder
public class PropertySummaryResponse {

    // Identity
    private UUID id;

    private String propertyCode;


    // Basic information
    private String title;

    private PropertyType propertyType;

    private ListingType listingType;


    // Location
    private String locality;

    private String city;

    private String state;


    // Pricing
    private BigDecimal expectedPrice;


    // Area
    private Double builtUpAreaSqFt;


    // Rooms
    private Integer bedrooms;

    private Integer bathrooms;

    private Integer parkingSpaces;


    // Search ranking
    private Double averageRating;


    // Media
    private String thumbnailUrl;


    // Display flags
    private Boolean verified;

    private Boolean featured;


    // Social proof
    private Long totalReviews;
}