package virtusa.project.domains.listings.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import virtusa.project.domains.listings.dto.CreatePropertyRequest;
import virtusa.project.domains.listings.dto.PropertyResponse;
import virtusa.project.domains.listings.dto.PropertySummaryResponse;
import virtusa.project.domains.listings.dto.UpdatePropertyRequest;
import virtusa.project.domains.listings.model.Property;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy =
                NullValuePropertyMappingStrategy.IGNORE
)
public interface PropertyMapper {


    // ==================================================
    // CREATE
    // ==================================================

    Property toEntity(
            CreatePropertyRequest request);


    // ==================================================
    // DETAILS RESPONSE
    // ==================================================

    PropertyResponse toResponse(
            Property property);


    // ==================================================
    // SEARCH CARD RESPONSE
    // ==================================================

    @Mapping(
            target = "thumbnailUrl",
            expression = "java(getThumbnail(property.getImageUrls()))"
    )
        @Mapping(
        target = "totalReviews",
        constant = "0L"
        )
    PropertySummaryResponse toSummary(
            Property property);


    // ==================================================
    // UPDATE
    // ==================================================

    void updateEntity(
            UpdatePropertyRequest request,
            @MappingTarget Property property);


    // ==================================================
    // CUSTOM HELPERS
    // ==================================================

    default String getThumbnail(
            List<String> images) {

        if (images == null || images.isEmpty()) {
            return null;
        }

        return images.get(0);
    }
}