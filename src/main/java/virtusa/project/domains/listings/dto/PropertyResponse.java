package virtusa.project.domains.listings.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    private Long id;

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
}