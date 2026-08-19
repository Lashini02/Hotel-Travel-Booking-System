package com.hotelbooking.reservation.controller;

import com.hotelbooking.reservation.dto.CreateReservationRequest;
import com.hotelbooking.reservation.dto.ReservationResponse;
import com.hotelbooking.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservation Controller", description = "Endpoints for managing hotel reservations, stay dates, cancellations, and user history (Student 3)")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @Operation(summary = "Create a new reservation", description = "Creates a new hotel booking across stay dates with guest information.")
    @ApiResponse(responseCode = "201", description = "Reservation created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request payload")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid API Key")
    public ResponseEntity<ReservationResponse> createReservation(
            @Valid @RequestBody CreateReservationRequest request) {
        ReservationResponse response = reservationService.createReservation(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get reservation by ID", description = "Retrieves details of a specific reservation using its unique ID.")
    @ApiResponse(responseCode = "200", description = "Reservation details found")
    @ApiResponse(responseCode = "404", description = "Reservation not found")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid API Key")
    public ResponseEntity<ReservationResponse> getReservationById(
            @Parameter(description = "Unique ID of the reservation") @PathVariable String id) {
        ReservationResponse response = reservationService.getReservationById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user booking history", description = "Retrieves all reservations made by a specific user.")
    @ApiResponse(responseCode = "200", description = "List of user reservations retrieved")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid API Key")
    public ResponseEntity<List<ReservationResponse>> getReservationsByUserId(
            @Parameter(description = "ID of the user") @PathVariable String userId) {
        List<ReservationResponse> responses = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping
    @Operation(summary = "Get all reservations", description = "Retrieves a complete list of all reservations in the system.")
    @ApiResponse(responseCode = "200", description = "List of all reservations retrieved")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid API Key")
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        List<ReservationResponse> responses = reservationService.getAllReservations();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}/cancel")
    @Operation(summary = "Cancel a reservation", description = "Updates the status of an existing reservation to CANCELLED.")
    @ApiResponse(responseCode = "200", description = "Reservation cancelled successfully")
    @ApiResponse(responseCode = "404", description = "Reservation not found")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid API Key")
    public ResponseEntity<ReservationResponse> cancelReservation(
            @Parameter(description = "Unique ID of the reservation to cancel") @PathVariable String id) {
        ReservationResponse response = reservationService.cancelReservation(id);
        return ResponseEntity.ok(response);
    }
}
