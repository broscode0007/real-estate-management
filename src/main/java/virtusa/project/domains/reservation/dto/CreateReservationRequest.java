package virtusa.project.domains.reservation.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import virtusa.project.domains.reservation.models.ReservationType;

@Getter
@Setter
public class CreateReservationRequest {

    @NotNull
    private UUID propertyId;

    @NotNull
    private ReservationType reservationType;

    private String notes;
}