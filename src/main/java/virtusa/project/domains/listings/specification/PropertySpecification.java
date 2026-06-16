package virtusa.project.domains.listings.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import virtusa.project.domains.listings.dto.PropertySearchRequest;
import virtusa.project.domains.listings.model.Property;
import virtusa.project.domains.listings.model.PropertyStatus;

public class PropertySpecification {

    private PropertySpecification() {
    }

    public static Specification<Property> search(
            PropertySearchRequest request) {

        return (root, query, cb) -> {

            List<Predicate> predicates =
                    new ArrayList<>();


            // ==================================================
            // Only active listings are publicly searchable
            // ==================================================

            predicates.add(
                    cb.equal(
                            root.get("status"),
                            PropertyStatus.ACTIVE));


            // ==================================================
            // Full text search
            // ==================================================

            if (hasText(request.getQuery())) {

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


            // ==================================================
            // Location filters
            // ==================================================

            addLikeFilter(
                    predicates,
                    cb,
                    root.get("city"),
                    request.getCity());

            addLikeFilter(
                    predicates,
                    cb,
                    root.get("state"),
                    request.getState());

            addLikeFilter(
                    predicates,
                    cb,
                    root.get("locality"),
                    request.getLocality());


            // ==================================================
            // Property filters
            // ==================================================

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


            // ==================================================
            // Price filters
            // ==================================================

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


            // ==================================================
            // Area filters
            // ==================================================

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


            // ==================================================
            // Room filters
            // ==================================================

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


            // ==================================================
            // Rating
            // ==================================================

            if (request.getMinRating() != null) {

                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("averageRating"),
                                request.getMinRating()));
            }


            // ==================================================
            // Reservation
            // ==================================================

            if (request.getReservationsEnabled() != null) {

                predicates.add(
                        cb.equal(
                                root.get("reservationsEnabled"),
                                request.getReservationsEnabled()));
            }


            return cb.and(
                    predicates.toArray(
                            new Predicate[0]));
        };
    }


    private static boolean hasText(
            String value) {

        return value != null &&
                !value.isBlank();
    }


    private static void addLikeFilter(
            List<Predicate> predicates,
            jakarta.persistence.criteria.CriteriaBuilder cb,
            jakarta.persistence.criteria.Expression<String> field,
            String value) {

        if (hasText(value)) {

            predicates.add(
                    cb.like(
                            cb.lower(field),
                            "%" +
                            value.toLowerCase()
                            + "%"));
        }
    }
}