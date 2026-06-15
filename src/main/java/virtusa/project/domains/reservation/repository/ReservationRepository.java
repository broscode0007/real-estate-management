package virtusa.project.domains.reservation.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import virtusa.project.domains.reservation.models.Reservation;
import virtusa.project.domains.reservation.models.ReservationStatus;
import virtusa.project.domains.reservation.models.ReservationType;

public interface ReservationRepository
        extends JpaRepository<Reservation, UUID> {

    /*
     * Buyer Queries
     */

    List<Reservation> findByBuyerFirebaseUidOrderByCreatedAtDesc(
            String buyerFirebaseUid);

    Optional<Reservation> findByIdAndBuyerFirebaseUid(
            UUID reservationId,
            String buyerFirebaseUid);

    /*
     * Agent Queries
     */

    List<Reservation> findByAgentFirebaseUidOrderByCreatedAtDesc(
            String agentFirebaseUid);

    /*
     * Property Queries
     */

    List<Reservation> findByPropertyId(
            UUID propertyId);

    List<Reservation> findByPropertyIdAndStatus(
            UUID propertyId,
            ReservationStatus status);

    /*
     * Immediate Reservation Lock
     */

    boolean existsByPropertyIdAndReservationTypeAndStatus(
            UUID propertyId,
            ReservationType reservationType,
            ReservationStatus status);

    Optional<Reservation>
            findByPropertyIdAndReservationTypeAndStatus(
                    UUID propertyId,
                    ReservationType reservationType,
                    ReservationStatus status);

    /*
     * Normal Reservation Limits
     */

    long countByPropertyIdAndReservationTypeAndStatus(
            UUID propertyId,
            ReservationType reservationType,
            ReservationStatus status);

    /*
     * Buyer History
     */

    long countByBuyerFirebaseUid(
            String buyerFirebaseUid);

    /*
     * Duplicate Prevention
     */

    boolean existsByBuyerFirebaseUidAndPropertyIdAndStatus(
            String buyerFirebaseUid,
            UUID propertyId,
            ReservationStatus status);
}