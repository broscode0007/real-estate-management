// GET /properties
// GET /search
// GET /agent/listings


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
public class PropertySearchRequest {

    
    // Text Search
    private String query;

    // Location
    private String city;

    private String state;

    private String locality;

    // Property
    private PropertyType propertyType;

    private ListingType listingType;

    // Price
    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    // Area
    private Double minAreaSqFt;

    private Double maxAreaSqFt;

    // Rooms
    private Integer minBedrooms;

    private Integer minBathrooms;

    // Rating
    private Double minRating;

    // Reservation
    private Boolean reservationsEnabled;

    // Pagination
    private Integer page;

    private Integer size;

    // Sorting
    private String sortBy;

    private String sortDirection;
}