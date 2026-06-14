package virtusa.project.domains.agent.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = false)
public class AgentProfileUpdateRequest {

    // Contact Information

    private String phoneNumber;
    private String displayName;
    private String profilePictureUrl;

    // Personal Information

    private String fullName;
    private String firstName;
    private String lastName;

    private String gender;
    private LocalDate dateOfBirth;

    private String nationality;
    private String language;
    private String timezone;

    // Profile Information

    private String bio;
    private String tagline;

    // Professional Information

    private String agencyName;
    private String agentLicenseNumber;

    private Integer yearsOfExperience;

    private String specialization;
    private String designation;

    // Location Information

    private String city;
    private String state;
    private String country;

    private String addressLine1;
    private String addressLine2;
    private String postalCode;

    private Double latitude;
    private Double longitude;

    // Notification Preferences

    private Boolean emailNotificationsEnabled;
    private Boolean pushNotificationsEnabled;
    private Boolean chatNotificationsEnabled;
    private Boolean marketingNotificationsEnabled;
}