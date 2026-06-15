package virtusa.project.domains.listings.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.agent.model.Agent;
import virtusa.project.domains.agent.repository.AgentRepository;
import virtusa.project.domains.listings.dto.CreatePropertyRequest;
import virtusa.project.domains.listings.dto.PropertyResponse;
import virtusa.project.domains.listings.dto.PropertySearchRequest;
import virtusa.project.domains.listings.dto.PropertySummaryResponse;
import virtusa.project.domains.listings.dto.UpdatePropertyRequest;
import virtusa.project.domains.listings.mapper.PropertyMapper;
import virtusa.project.domains.listings.model.Property;
import virtusa.project.domains.listings.model.PropertyStatus;
import virtusa.project.domains.listings.repository.PropertyRepository;
import virtusa.project.domains.listings.specification.PropertySpecification;
import virtusa.project.exceptions.BadRequestException;
import virtusa.project.exceptions.ResourceNotFoundException;
import virtusa.project.exceptions.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final AgentRepository agentRepository;

    @Override
    public PropertyResponse createProperty(
            String agentFirebaseUid,
            CreatePropertyRequest request) {

        Agent agent = agentRepository
                .findById(agentFirebaseUid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Agent not found"));

        Property property =
                PropertyMapper.toEntity(request);

        property.setAgent(agent);

        property.setPropertyCode(
                generatePropertyCode());

        property.setStatus(PropertyStatus.DRAFT);

        property.setCreatedAt(LocalDateTime.now());

        property.setUpdatedAt(LocalDateTime.now());

        Property saved =
                propertyRepository.save(property);

        return PropertyMapper.toResponse(saved);
    }

    @Override
    public PropertyResponse updateProperty(
            UUID propertyId,
            String agentFirebaseUid,
            UpdatePropertyRequest request) {

        Property property = propertyRepository
                .findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Property not found"));

        if (!property.getAgent()
                .getFirebaseUid()
                .equals(agentFirebaseUid)) {

            throw new UnauthorizedException(
                    "You do not own this property");
        }

        PropertyMapper.updateEntity(
                property,
                request);

        property.setUpdatedAt(
                LocalDateTime.now());

        Property saved =
                propertyRepository.save(property);

        return PropertyMapper.toResponse(saved);
    }

    @Override
    public PropertyResponse getProperty(
            UUID propertyId) {

        Property property = propertyRepository
                .findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Property not found"));

        return PropertyMapper.toResponse(property);
    }

        @Override
        public Page<PropertySummaryResponse> searchProperties(
                PropertySearchRequest request) {

        Pageable pageable =
                PageRequest.of(
                        request.getPage() == null
                                ? 0
                                : request.getPage(),

                        request.getSize() == null
                                ? 20
                                : request.getSize());

        return propertyRepository
                .findAll(
                        PropertySpecification
                                .search(request),
                        pageable)
                .map(PropertyMapper::toSummary);
        }

        @Override
        public Page<PropertySummaryResponse> getAgentProperties(
                String agentFirebaseUid,
                int page,
                int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return propertyRepository
                .findByAgentFirebaseUid(
                        agentFirebaseUid,
                        pageable)
                .map(PropertyMapper::toSummary);
        }

        @Override
        public void deleteProperty(
                UUID propertyId,
                String agentFirebaseUid) {

        Property property = propertyRepository
                .findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Property not found"));

        if (!property.getAgent()
                .getFirebaseUid()
                .equals(agentFirebaseUid)) {

                throw new UnauthorizedException(
                        "You do not own this property");
        }

        if (Boolean.TRUE.equals(
                property.getVerified())) {

                throw new BadRequestException(
                        "Verified properties cannot be deleted");
        }

        propertyRepository.delete(property);
        }

        @Override
        public void deactivateProperty(
                UUID propertyId,
                String agentFirebaseUid) {

        Property property = propertyRepository
                .findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Property not found"));

        if (!property.getAgent()
                .getFirebaseUid()
                .equals(agentFirebaseUid)) {

                throw new UnauthorizedException(
                        "You do not own this property");
        }

        property.setStatus(
                PropertyStatus.INACTIVE);

        property.setUpdatedAt(
                LocalDateTime.now());

        propertyRepository.save(property);
        }

private String generatePropertyCode() {

    return "PROP-" +
            UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase();
}
}
