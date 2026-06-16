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


    // ==================================================
    // Full text search
    // ==================================================

    private String query;


    // ==================================================
    // Location filters
    // ==================================================

    private String city;

    private String state;

    private String locality;


    // ==================================================
    // Property filters
    // ==================================================

    private PropertyType propertyType;

    private ListingType listingType;


    // ==================================================
    // Price filters
    // ==================================================

    private BigDecimal minPrice;

    private BigDecimal maxPrice;


    // ==================================================
    // Area filters
    // ==================================================

    private Double minAreaSqFt;

    private Double maxAreaSqFt;


    // ==================================================
    // Room filters
    // ==================================================

    private Integer minBedrooms;

    private Integer minBathrooms;


    // ==================================================
    // Rating / feature filters
    // ==================================================

    private Double minRating;

    private Boolean reservationsEnabled;


    // ==================================================
    // Pagination
    // ==================================================

    private Integer page;

    private Integer size;


    // ==================================================
    // Sorting
    // ==================================================

    private String sortBy;

    private String sortDirection;
}