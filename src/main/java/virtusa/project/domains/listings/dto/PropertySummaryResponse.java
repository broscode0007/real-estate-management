// GET /properties
// GET /search
// GET /agent/listings
// GET /favorites


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
public class PropertySummaryResponse {

    private Long id;

    private String propertyCode;

    private String title;

    private PropertyType propertyType;

    private ListingType listingType;

    private BigDecimal expectedPrice;

    private String locality;

    private String city;

    private Integer bedrooms;

    private Integer bathrooms;

    private Integer parkingSpaces;

    private Double builtUpAreaSqFt;

    private Double averageRating;

    private Long totalReviews;

    private Boolean verified;

    private Boolean featured;

    private String thumbnailUrl;
}