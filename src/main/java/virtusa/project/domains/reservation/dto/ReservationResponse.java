package virtusa.project.domains.reservation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import virtusa.project.domains.reservation.models.ReservationStatus;
import virtusa.project.domains.reservation.models.ReservationType;

@Getter
@Builder
public class ReservationResponse {

    private final UUID id;

    private final UUID propertyId;

    private final String buyerFirebaseUid;

    private final String agentFirebaseUid;

    private final ReservationType reservationType;

    private final ReservationStatus status;

    private final Double amountPaid;

    private final String paymentId;

    private final LocalDateTime expiresAt;

    private final String notes;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
}