package virtusa.project.domains.buyers.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerProfileUpdateRequest {

    private String firstName;

    private String lastName;

    private String fullName;

    private String gender;

    private LocalDate dateOfBirth;

    private String nationality;

    private String language;

    private String timezone;

    private String bio;

    private String city;

    private String state;

    private String country;

    private String addressLine1;

    private String addressLine2;

    private String postalCode;

    private Double latitude;

    private Double longitude;

    private String preferredPropertyType;

    private String preferredListingType;

    private String preferredCity;

    private String preferredState;

    private String preferredCountry;

    private Double minBudget;

    private Double maxBudget;

    private Integer minBedrooms;

    private Integer maxBedrooms;

    private Integer minBathrooms;

    private Integer maxBathrooms;

    private LocalDate preferredMoveInDate;

    private boolean investmentBuyer;

    private boolean firstTimeBuyer;

    private boolean emailNotificationsEnabled;

    private boolean pushNotificationsEnabled;

    private boolean reservationNotificationsEnabled;

    private boolean marketingNotificationsEnabled;

    private boolean priceDropNotificationsEnabled;
}