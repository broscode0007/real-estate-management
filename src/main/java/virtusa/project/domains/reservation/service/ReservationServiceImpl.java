package virtusa.project.domains.reservation.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.buyers.model.Buyer;
import virtusa.project.domains.buyers.repository.BuyerRepository;
import virtusa.project.domains.listings.model.Property;
import virtusa.project.domains.listings.repository.PropertyRepository;
import virtusa.project.domains.reservation.dto.CreateReservationRequest;
import virtusa.project.domains.reservation.dto.ReservationResponse;
import virtusa.project.domains.reservation.models.Reservation;
import virtusa.project.domains.reservation.models.ReservationStatus;
import virtusa.project.domains.reservation.models.ReservationType;
import virtusa.project.domains.reservation.repository.ReservationRepository;
import virtusa.project.exceptions.AccountDeletedException;
import virtusa.project.exceptions.BadRequestException;
import virtusa.project.exceptions.ResourceNotFoundException;
import virtusa.project.exceptions.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final PropertyRepository propertyRepository;
    private final BuyerRepository buyerRepository;

    @Override
    public ReservationResponse createReservation(
            Authentication authentication,
            CreateReservationRequest request) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        Property property = propertyRepository
                .findById(request.getPropertyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Property not found"));

        validateReservationEligibility(
                property,
                request.getReservationType());

        validateDuplicateReservation(
                buyer.getFirebaseUid(),
                property.getId());

        validateReservationLimits(
                property,
                request.getReservationType());

        Double amountPaid =
                getReservationAmount(
                        request.getReservationType());

        Reservation reservation =
                Reservation.builder()
                        .buyerFirebaseUid(
                                buyer.getFirebaseUid())
                        .agentFirebaseUid(
                                property.getAgent()
                                        .getFirebaseUid())
                        .propertyId(
                                property.getId())
                        .reservationType(
                                request.getReservationType())
                        .status(
                                ReservationStatus.ACTIVE)
                        .amountPaid(amountPaid)
                        .paymentId(
                                "MOCK-" +
                                UUID.randomUUID())
                        .notes(
                                request.getNotes())
                        .build();

        reservation =
                reservationRepository.save(
                        reservation);

        return mapToResponse(reservation);
    }

    @Override
    public List<ReservationResponse> getMyReservations(
            Authentication authentication) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        return reservationRepository
                .findByBuyerFirebaseUidOrderByCreatedAtDesc(
                        buyer.getFirebaseUid())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ReservationResponse getReservation(
            Authentication authentication,
            UUID reservationId) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        Reservation reservation =
                reservationRepository
                        .findByIdAndBuyerFirebaseUid(
                                reservationId,
                                buyer.getFirebaseUid())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reservation not found"));

        return mapToResponse(reservation);
    }

    @Override
    public ReservationResponse cancelReservation(
            Authentication authentication,
            UUID reservationId) {

        Buyer buyer = getAuthenticatedBuyer(authentication);

        Reservation reservation =
                reservationRepository
                        .findByIdAndBuyerFirebaseUid(
                                reservationId,
                                buyer.getFirebaseUid())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Reservation not found"));

        reservation.setStatus(
                ReservationStatus.CANCELLED);

        reservation =
                reservationRepository.save(
                        reservation);

        return mapToResponse(reservation);
    }

    private void validateReservationEligibility(
            Property property,
            ReservationType type) {

        if (!Boolean.TRUE.equals(
                property.getReservationsEnabled())) {

            throw new BadRequestException(
                    "Reservations are disabled for this property");
        }

        switch (type) {

            case SLOW -> {

                if (!Boolean.TRUE.equals(
                        property.getSlowReservationEnabled())) {

                    throw new BadRequestException(
                            "Slow reservation unavailable");
                }
            }

            case NORMAL -> {

                if (!Boolean.TRUE.equals(
                        property.getNormalReservationEnabled())) {

                    throw new BadRequestException(
                            "Normal reservation unavailable");
                }
            }

            case IMMEDIATE -> {

                if (!Boolean.TRUE.equals(
                        property.getImmediateReservationEnabled())) {

                    throw new BadRequestException(
                            "Immediate reservation unavailable");
                }
            }
        }
    }

    private void validateDuplicateReservation(
            String buyerFirebaseUid,
            UUID propertyId) {

        boolean alreadyReserved =
                reservationRepository
                        .existsByBuyerFirebaseUidAndPropertyIdAndStatus(
                                buyerFirebaseUid,
                                propertyId,
                                ReservationStatus.ACTIVE);

        if (alreadyReserved) {

            throw new BadRequestException(
                    "You already have an active reservation");
        }
    }

    private void validateReservationLimits(
            Property property,
            ReservationType type) {

        if (type == ReservationType.IMMEDIATE) {

            boolean locked =
                    reservationRepository
                            .existsByPropertyIdAndReservationTypeAndStatus(
                                    property.getId(),
                                    ReservationType.IMMEDIATE,
                                    ReservationStatus.ACTIVE);

            if (locked) {

                throw new BadRequestException(
                        "Property already has an active immediate reservation");
            }
        }

        if (type == ReservationType.NORMAL) {

            long activeReservations =
                    reservationRepository
                            .countByPropertyIdAndReservationTypeAndStatus(
                                    property.getId(),
                                    ReservationType.NORMAL,
                                    ReservationStatus.ACTIVE);

            if (activeReservations >=
                    property.getMaxNormalReservations()) {

                throw new BadRequestException(
                        "Maximum normal reservations reached");
            }
        }
    }

    private Double getReservationAmount(
            ReservationType type) {

        return switch (type) {

            case SLOW -> 99.0;

            case NORMAL -> 200.0;

            case IMMEDIATE -> 1000.0;
        };
    }

    private Buyer getAuthenticatedBuyer(
            Authentication authentication) {

        if (authentication == null ||
                authentication.getName() == null) {

            throw new UnauthorizedException(
                    "Authentication required");
        }

        Buyer buyer =
                buyerRepository
                        .findByFirebaseUid(
                                authentication.getName())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Buyer not found"));

        if (buyer.isAccountDeleted()) {

            throw new AccountDeletedException();
        }

        return buyer;
    }

    private ReservationResponse mapToResponse(
            Reservation reservation) {

        return ReservationResponse.builder()
                .id(reservation.getId())
                .propertyId(
                        reservation.getPropertyId())
                .buyerFirebaseUid(
                        reservation.getBuyerFirebaseUid())
                .agentFirebaseUid(
                        reservation.getAgentFirebaseUid())
                .reservationType(
                        reservation.getReservationType())
                .status(
                        reservation.getStatus())
                .amountPaid(
                        reservation.getAmountPaid())
                .paymentId(
                        reservation.getPaymentId())
                .expiresAt(
                        reservation.getExpiresAt())
                .notes(
                        reservation.getNotes())
                .createdAt(
                        reservation.getCreatedAt())
                .updatedAt(
                        reservation.getUpdatedAt())
                .build();
    }
}