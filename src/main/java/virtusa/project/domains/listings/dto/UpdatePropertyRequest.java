// POST   /agent/listings
// PUT    /agent/listings/{id}

package virtusa.project.domains.listings.dto;

import java.math.BigDecimal;

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
public class UpdatePropertyRequest {

    private String title;

    private String description;

    private PropertyType propertyType;

    private ListingType listingType;

    private PropertyStatus status;

    private BigDecimal startPrice;

    private BigDecimal expectedPrice;

    private BigDecimal maxPrice;

    private String address;

    private String locality;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private Double latitude;

    private Double longitude;

    private Double builtUpAreaSqFt;

    private Double carpetAreaSqFt;

    private Integer bedrooms;

    private Integer bathrooms;

    private Integer balconies;

    private Integer parkingSpaces;

    private Boolean reservationsEnabled;

    private Boolean immediateReservationEnabled;

    private Boolean normalReservationEnabled;

    private Boolean slowReservationEnabled;

    private Integer maxNormalReservations;

    private String district;
}