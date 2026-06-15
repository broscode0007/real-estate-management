package virtusa.project.domains.listings.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import virtusa.project.domains.listings.dto.CreatePropertyRequest;
import virtusa.project.domains.listings.dto.PropertyResponse;
import virtusa.project.domains.listings.dto.PropertySearchRequest;
import virtusa.project.domains.listings.dto.PropertySummaryResponse;
import virtusa.project.domains.listings.dto.UpdatePropertyRequest;

public interface PropertyService {

    PropertyResponse createProperty(
            String agentFirebaseUid,
            CreatePropertyRequest request);

    PropertyResponse updateProperty(
            UUID propertyId,
            String agentFirebaseUid,
            UpdatePropertyRequest request);

    PropertyResponse getProperty(
            UUID propertyId);

    Page<PropertySummaryResponse> searchProperties(
            PropertySearchRequest request);

    Page<PropertySummaryResponse> getAgentProperties(
            String agentFirebaseUid,
            int page,
            int size);
void deactivateProperty(
        UUID propertyId,
        String agentFirebaseUid);

    void deleteProperty(
        UUID propertyId,
        String agentFirebaseUid);
}