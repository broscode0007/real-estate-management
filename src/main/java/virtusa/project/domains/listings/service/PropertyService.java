package virtusa.project.domains.listings.service;

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
            Long propertyId,
            String agentFirebaseUid,
            UpdatePropertyRequest request);

    PropertyResponse getProperty(
            Long propertyId);

    Page<PropertySummaryResponse> searchProperties(
            PropertySearchRequest request);

    Page<PropertySummaryResponse> getAgentProperties(
            String agentFirebaseUid,
            int page,
            int size);

    void deleteProperty(
            Long propertyId,
            String agentFirebaseUid);
}