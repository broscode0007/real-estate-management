package virtusa.project.domains.agent.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.agent.dto.AgentProfileUpdateRequest;
import virtusa.project.domains.agent.model.Agent;
import virtusa.project.domains.agent.repository.AgentRepository;

@Service
@RequiredArgsConstructor
public class AgentProfileService {

    private final AgentRepository agentRepository;

    public String getFirebaseUidFromToken(String idToken)
            throws Exception {

        FirebaseToken decodedToken =
                FirebaseAuth.getInstance().verifyIdToken(idToken);

        return decodedToken.getUid();
    }

    public Agent getProfile(String firebaseUid) {

        return agentRepository.findById(firebaseUid)
                .orElseThrow(() ->
                        new RuntimeException("Agent not found"));
    }

    @Transactional
    public Agent updateProfile(
            String firebaseUid,
            AgentProfileUpdateRequest request) {

        Agent agent = getProfile(firebaseUid);

        // Contact

        agent.setPhoneNumber(request.getPhoneNumber());
        agent.setDisplayName(request.getDisplayName());
        agent.setProfilePictureUrl(request.getProfilePictureUrl());

        // Personal

        agent.setFullName(request.getFullName());
        agent.setFirstName(request.getFirstName());
        agent.setLastName(request.getLastName());

        agent.setGender(request.getGender());
        agent.setDateOfBirth(request.getDateOfBirth());

        agent.setNationality(request.getNationality());
        agent.setLanguage(request.getLanguage());
        agent.setTimezone(request.getTimezone());

        // Profile

        agent.setBio(request.getBio());
        agent.setTagline(request.getTagline());

        // Professional

        agent.setAgencyName(request.getAgencyName());
        agent.setAgentLicenseNumber(request.getAgentLicenseNumber());

        agent.setYearsOfExperience(
                request.getYearsOfExperience());

        agent.setSpecialization(
                request.getSpecialization());

        agent.setDesignation(
                request.getDesignation());

        // Location

        agent.setCity(request.getCity());
        agent.setState(request.getState());
        agent.setCountry(request.getCountry());

        agent.setAddressLine1(
                request.getAddressLine1());

        agent.setAddressLine2(
                request.getAddressLine2());

        agent.setPostalCode(
                request.getPostalCode());

        agent.setLatitude(
                request.getLatitude());

        agent.setLongitude(
                request.getLongitude());

        // Notifications

        if (request.getEmailNotificationsEnabled() != null) {
            agent.setEmailNotificationsEnabled(
                    request.getEmailNotificationsEnabled());
        }

        if (request.getPushNotificationsEnabled() != null) {
            agent.setPushNotificationsEnabled(
                    request.getPushNotificationsEnabled());
        }

        if (request.getChatNotificationsEnabled() != null) {
            agent.setChatNotificationsEnabled(
                    request.getChatNotificationsEnabled());
        }

        if (request.getMarketingNotificationsEnabled() != null) {
            agent.setMarketingNotificationsEnabled(
                    request.getMarketingNotificationsEnabled());
        }

        return agentRepository.save(agent);
    }

    @Transactional
    public Agent patchProfile(
            String firebaseUid,
            AgentProfileUpdateRequest request) {

        Agent agent = getProfile(firebaseUid);

        // Contact

        if (request.getPhoneNumber() != null)
            agent.setPhoneNumber(request.getPhoneNumber());

        if (request.getDisplayName() != null)
            agent.setDisplayName(request.getDisplayName());

        if (request.getProfilePictureUrl() != null)
            agent.setProfilePictureUrl(
                    request.getProfilePictureUrl());

        // Personal

        if (request.getFullName() != null)
            agent.setFullName(request.getFullName());

        if (request.getFirstName() != null)
            agent.setFirstName(request.getFirstName());

        if (request.getLastName() != null)
            agent.setLastName(request.getLastName());

        if (request.getGender() != null)
            agent.setGender(request.getGender());

        if (request.getDateOfBirth() != null)
            agent.setDateOfBirth(request.getDateOfBirth());

        if (request.getNationality() != null)
            agent.setNationality(request.getNationality());

        if (request.getLanguage() != null)
            agent.setLanguage(request.getLanguage());

        if (request.getTimezone() != null)
            agent.setTimezone(request.getTimezone());

        // Profile

        if (request.getBio() != null)
            agent.setBio(request.getBio());

        if (request.getTagline() != null)
            agent.setTagline(request.getTagline());

        // Professional

        if (request.getAgencyName() != null)
            agent.setAgencyName(request.getAgencyName());

        if (request.getAgentLicenseNumber() != null)
            agent.setAgentLicenseNumber(
                    request.getAgentLicenseNumber());

        if (request.getYearsOfExperience() != null)
            agent.setYearsOfExperience(
                    request.getYearsOfExperience());

        if (request.getSpecialization() != null)
            agent.setSpecialization(
                    request.getSpecialization());

        if (request.getDesignation() != null)
            agent.setDesignation(
                    request.getDesignation());

        // Location

        if (request.getCity() != null)
            agent.setCity(request.getCity());

        if (request.getState() != null)
            agent.setState(request.getState());

        if (request.getCountry() != null)
            agent.setCountry(request.getCountry());

        if (request.getAddressLine1() != null)
            agent.setAddressLine1(
                    request.getAddressLine1());

        if (request.getAddressLine2() != null)
            agent.setAddressLine2(
                    request.getAddressLine2());

        if (request.getPostalCode() != null)
            agent.setPostalCode(
                    request.getPostalCode());

        if (request.getLatitude() != null)
            agent.setLatitude(
                    request.getLatitude());

        if (request.getLongitude() != null)
            agent.setLongitude(
                    request.getLongitude());

        // Notifications

        if (request.getEmailNotificationsEnabled() != null)
            agent.setEmailNotificationsEnabled(
                    request.getEmailNotificationsEnabled());

        if (request.getPushNotificationsEnabled() != null)
            agent.setPushNotificationsEnabled(
                    request.getPushNotificationsEnabled());

        if (request.getChatNotificationsEnabled() != null)
            agent.setChatNotificationsEnabled(
                    request.getChatNotificationsEnabled());

        if (request.getMarketingNotificationsEnabled() != null)
            agent.setMarketingNotificationsEnabled(
                    request.getMarketingNotificationsEnabled());

        return agentRepository.save(agent);
    }

    @Transactional
    public void deleteProfile(String firebaseUid) {

        Agent agent = getProfile(firebaseUid);

        agent.setActive(false);
        agent.setDeleted(true);

        agentRepository.save(agent);
    }
}