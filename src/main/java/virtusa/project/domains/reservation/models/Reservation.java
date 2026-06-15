package virtusa.project.domains.reservation.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "buyer_firebase_uid", nullable = false)
    private String buyerFirebaseUid;

    @Column(name = "agent_firebase_uid", nullable = false)
    private String agentFirebaseUid;

    @Column(name = "property_id", nullable = false)
    private UUID propertyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_type", nullable = false)
    private ReservationType reservationType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ReservationStatus status =
            ReservationStatus.PENDING_PAYMENT;

    /*
     * Payment
     */

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_order_id")
    private String paymentOrderId;

    @Column(name = "payment_signature")
    private String paymentSignature;

    @Column(name = "amount_paid")
    private Double amountPaid;

    /*
     * Reservation Metadata
     */

    @Column(length = 1000)
    private String notes;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    /*
     * Audit
     */

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {

        LocalDateTime now = LocalDateTime.now();

        if (this.id == null) {
            this.id = UUID.randomUUID();
        }

        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}