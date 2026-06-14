package virtusa.project.domains.listings.mapper;

import virtusa.project.domains.listings.dto.CreatePropertyRequest;
import virtusa.project.domains.listings.dto.PropertyResponse;
import virtusa.project.domains.listings.dto.PropertySummaryResponse;
import virtusa.project.domains.listings.dto.UpdatePropertyRequest;
import virtusa.project.domains.listings.model.Property;

public final class PropertyMapper {

    private PropertyMapper() {
    }

    public static Property toEntity(CreatePropertyRequest request) {

        return Property.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .propertyType(request.getPropertyType())
                .listingType(request.getListingType())
                .startPrice(request.getStartPrice())
                .expectedPrice(request.getExpectedPrice())
                .maxPrice(request.getMaxPrice())
                .address(request.getAddress())
                .locality(request.getLocality())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .builtUpAreaSqFt(request.getBuiltUpAreaSqFt())
                .carpetAreaSqFt(request.getCarpetAreaSqFt())
                .bedrooms(request.getBedrooms())
                .bathrooms(request.getBathrooms())
                .balconies(request.getBalconies())
                .parkingSpaces(request.getParkingSpaces())
                .reservationsEnabled(request.getReservationsEnabled())
                .immediateReservationEnabled(
                        request.getImmediateReservationEnabled())
                .normalReservationEnabled(
                        request.getNormalReservationEnabled())
                .slowReservationEnabled(
                        request.getSlowReservationEnabled())
                .maxNormalReservations(
                        request.getMaxNormalReservations())
                .build();
    }

    public static void updateEntity(
            Property property,
            UpdatePropertyRequest request) {

        property.setTitle(request.getTitle());
        property.setDescription(request.getDescription());
        property.setPropertyType(request.getPropertyType());
        property.setListingType(request.getListingType());
        property.setStatus(request.getStatus());

        property.setStartPrice(request.getStartPrice());
        property.setExpectedPrice(request.getExpectedPrice());
        property.setMaxPrice(request.getMaxPrice());

        property.setAddress(request.getAddress());
        property.setLocality(request.getLocality());
        property.setCity(request.getCity());
        property.setState(request.getState());
        property.setCountry(request.getCountry());
        property.setPostalCode(request.getPostalCode());

        property.setLatitude(request.getLatitude());
        property.setLongitude(request.getLongitude());

        property.setBuiltUpAreaSqFt(request.getBuiltUpAreaSqFt());
        property.setCarpetAreaSqFt(request.getCarpetAreaSqFt());

        property.setBedrooms(request.getBedrooms());
        property.setBathrooms(request.getBathrooms());
        property.setBalconies(request.getBalconies());
        property.setParkingSpaces(request.getParkingSpaces());

        property.setReservationsEnabled(
                request.getReservationsEnabled());

        property.setImmediateReservationEnabled(
                request.getImmediateReservationEnabled());

        property.setNormalReservationEnabled(
                request.getNormalReservationEnabled());

        property.setSlowReservationEnabled(
                request.getSlowReservationEnabled());

        property.setMaxNormalReservations(
                request.getMaxNormalReservations());
    }

    public static PropertyResponse toResponse(Property property) {

        return PropertyResponse.builder()
                .id(property.getId())
                .propertyCode(property.getPropertyCode())
                .title(property.getTitle())
                .description(property.getDescription())
                .propertyType(property.getPropertyType())
                .listingType(property.getListingType())
                .status(property.getStatus())
                .expectedPrice(property.getExpectedPrice())
                .startPrice(property.getStartPrice())
                .maxPrice(property.getMaxPrice())
                .locality(property.getLocality())
                .city(property.getCity())
                .state(property.getState())
                .country(property.getCountry())
                .latitude(property.getLatitude())
                .longitude(property.getLongitude())
                .bedrooms(property.getBedrooms())
                .bathrooms(property.getBathrooms())
                .parkingSpaces(property.getParkingSpaces())
                .builtUpAreaSqFt(property.getBuiltUpAreaSqFt())
                .averageRating(property.getAverageRating())
                // .totalReviews(property.getTotalReviews())
                .viewCount(property.getViewCount())
                .favoriteCount(property.getFavoriteCount())
                .verified(property.getVerified())
                .featured(property.getFeatured())
                .createdAt(property.getCreatedAt())
                .build();
    }

    public static PropertySummaryResponse toSummary(
            Property property) {

        return PropertySummaryResponse.builder()
                .id(property.getId())
                .propertyCode(property.getPropertyCode())
                .title(property.getTitle())
                .propertyType(property.getPropertyType())
                .listingType(property.getListingType())
                .expectedPrice(property.getExpectedPrice())
                .locality(property.getLocality())
                .city(property.getCity())
                .bedrooms(property.getBedrooms())
                .bathrooms(property.getBathrooms())
                .parkingSpaces(property.getParkingSpaces())
                .builtUpAreaSqFt(property.getBuiltUpAreaSqFt())
                .averageRating(property.getAverageRating())
                // .totalReviews(property.getTotalReviews())
                .verified(property.getVerified())
                .featured(property.getFeatured())
                .build();
    }
}