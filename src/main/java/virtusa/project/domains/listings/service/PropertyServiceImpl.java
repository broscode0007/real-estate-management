package virtusa.project.domains.listings.service;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true)
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final AgentRepository agentRepository;
    private final PropertyMapper propertyMapper;


    @Override
    @Transactional
    public PropertyResponse createProperty(
            String agentFirebaseUid,
            CreatePropertyRequest request) {

        Agent agent = agentRepository
                .findById(agentFirebaseUid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Agent not found"));

        Property property =
                propertyMapper.toEntity(request);

        property.setAgent(agent);

        property.setPropertyCode(generatePropertyCode());

        property.setStatus(PropertyStatus.ACTIVE);

        property.setCreatedAt(LocalDateTime.now());

        property.setUpdatedAt(LocalDateTime.now());

        Property saved = propertyRepository.save(property);

        return propertyMapper.toResponse(saved);
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

        propertyMapper.updateEntity(
                request,
                property);

        property.setUpdatedAt(LocalDateTime.now());

        Property saved = propertyRepository.save(property);

        return propertyMapper.toResponse(saved);
    }


    @Override
    public PropertyResponse getProperty(
            UUID propertyId) {

        Property property = propertyRepository
                .findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Property not found"));

        return propertyMapper.toResponse(property);
    }


        @Override
        public Page<PropertySummaryResponse> searchProperties(
                PropertySearchRequest request) {

        String sortBy =
                request.getSortBy() != null
                        ? request.getSortBy()
                        : "createdAt";


        Sort.Direction direction =
                request.getSortDirection() != null
                        ? Sort.Direction.fromString(
                                request.getSortDirection())
                        : Sort.Direction.DESC;


        Sort sort = Sort.by(
                direction,
                sortBy
        );

        int page =
                request.getPage() != null
                        ? request.getPage()
                        : 0;


        int size =
                request.getSize() != null
                        ? request.getSize()
                        : 20;


        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );

        return propertyRepository
                .findAll(
                        PropertySpecification.search(request),
                        pageable
                )
                .map(propertyMapper::toSummary);
        }


    @Override
    public Page<PropertySummaryResponse> getAgentProperties(
            String agentFirebaseUid,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return propertyRepository
                .findByAgentFirebaseUid(
                        agentFirebaseUid,
                        pageable)
                .map(propertyMapper::toSummary);
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

        if (Boolean.TRUE.equals(property.getVerified())) {

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

        property.setStatus(PropertyStatus.INACTIVE);

        property.setUpdatedAt(LocalDateTime.now());

        propertyRepository.save(property);
    }


    @Override
    public void submitProperty(
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

        if (property.getStatus() != PropertyStatus.DRAFT) {

            throw new BadRequestException(
                    "Only draft properties can be submitted");
        }

        property.setStatus(PropertyStatus.SUBMITTED);

        property.setUpdatedAt(LocalDateTime.now());

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