package virtusa.project.domains.listings.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import virtusa.project.domains.listings.dto.PropertySearchRequest;
import virtusa.project.domains.listings.model.Property;
import virtusa.project.domains.listings.model.PropertyStatus;

public class PropertySpecification {

    public static Specification<Property> search(
            PropertySearchRequest request) {

        return (root, query, cb) -> {

            List<Predicate> predicates =
                    new ArrayList<>();

            // Only public active properties
            predicates.add(
                    cb.equal(
                            root.get("status"),
                            PropertyStatus.ACTIVE));

            if (request.getCity() != null) {

                predicates.add(
                        cb.equal(
                                root.get("city"),
                                request.getCity()));
            }

            if (request.getState() != null) {

                predicates.add(
                        cb.equal(
                                root.get("state"),
                                request.getState()));
            }

            if (request.getLocality() != null) {

                predicates.add(
                        cb.like(
                                cb.lower(
                                        root.get("locality")),
                                "%" +
                                request.getLocality()
                                        .toLowerCase()
                                + "%"));
            }

            if (request.getPropertyType() != null) {

                predicates.add(
                        cb.equal(
                                root.get("propertyType"),
                                request.getPropertyType()));
            }

            if (request.getListingType() != null) {

                predicates.add(
                        cb.equal(
                                root.get("listingType"),
                                request.getListingType()));
            }

            if (request.getMinPrice() != null) {

                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("expectedPrice"),
                                request.getMinPrice()));
            }

            if (request.getMaxPrice() != null) {

                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("expectedPrice"),
                                request.getMaxPrice()));
            }

            if (request.getMinBedrooms() != null) {

                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("bedrooms"),
                                request.getMinBedrooms()));
            }

            if (request.getMinBathrooms() != null) {

                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("bathrooms"),
                                request.getMinBathrooms()));
            }

            if (request.getMinAreaSqFt() != null) {

                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("builtUpAreaSqFt"),
                                request.getMinAreaSqFt()));
            }

            if (request.getMaxAreaSqFt() != null) {

                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("builtUpAreaSqFt"),
                                request.getMaxAreaSqFt()));
            }

            if (request.getReservationsEnabled() != null) {

                predicates.add(
                        cb.equal(
                                root.get("reservationsEnabled"),
                                request.getReservationsEnabled()));
            }

            if (request.getQuery() != null &&
                    !request.getQuery().isBlank()) {

                String search =
                        "%" +
                        request.getQuery()
                                .toLowerCase()
                        + "%";

                predicates.add(
                        cb.or(
                                cb.like(
                                        cb.lower(
                                                root.get("title")),
                                        search),
                                cb.like(
                                        cb.lower(
                                                root.get("description")),
                                        search),
                                cb.like(
                                        cb.lower(
                                                root.get("city")),
                                        search),
                                cb.like(
                                        cb.lower(
                                                root.get("locality")),
                                        search)
                        ));
            }

            return cb.and(
                    predicates.toArray(
                            new Predicate[0]));
        };
    }
}