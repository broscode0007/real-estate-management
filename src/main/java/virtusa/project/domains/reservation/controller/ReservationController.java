package virtusa.project.domains.reservation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import virtusa.project.domains.reservation.dto.CreateReservationRequest;
import virtusa.project.domains.reservation.dto.ReservationResponse;
import virtusa.project.domains.reservation.service.ReservationService;

@RestController
@RequestMapping("/api/v1/buyers/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(
            Authentication authentication,
            @Valid @RequestBody CreateReservationRequest request) {

        return ResponseEntity.ok(
                reservationService.createReservation(
                        authentication,
                        request));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getMyReservations(
            Authentication authentication) {

        return ResponseEntity.ok(
                reservationService.getMyReservations(
                        authentication));
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(
            Authentication authentication,
            @PathVariable UUID reservationId) {

        return ResponseEntity.ok(
                reservationService.getReservation(
                        authentication,
                        reservationId));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> cancelReservation(
            Authentication authentication,
            @PathVariable UUID reservationId) {

        return ResponseEntity.ok(
                reservationService.cancelReservation(
                        authentication,
                        reservationId));
    }
}