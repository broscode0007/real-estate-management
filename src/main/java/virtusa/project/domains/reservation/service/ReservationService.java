package virtusa.project.domains.reservation.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;

import virtusa.project.domains.reservation.dto.CreateReservationRequest;
import virtusa.project.domains.reservation.dto.ReservationResponse;

public interface ReservationService {

    ReservationResponse createReservation(
            Authentication authentication,
            CreateReservationRequest request);

    List<ReservationResponse> getMyReservations(
            Authentication authentication);

    ReservationResponse getReservation(
            Authentication authentication,
            UUID reservationId);

    ReservationResponse cancelReservation(
            Authentication authentication,
            UUID reservationId);
}