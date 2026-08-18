package com.hotelbooking.reservation.service;

import com.hotelbooking.reservation.dto.CreateReservationRequest;
import com.hotelbooking.reservation.dto.ReservationResponse;
import com.hotelbooking.reservation.exception.ResourceNotFoundException;
import com.hotelbooking.reservation.model.Reservation;
import com.hotelbooking.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponse createReservation(CreateReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setUserId(request.getUserId());
        reservation.setHotelId(request.getHotelId());
        reservation.setHotelName(request.getHotelName());
        reservation.setRoomId(request.getRoomId());
        reservation.setRoomType(request.getRoomType());
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setGuestName(request.getGuestName());
        reservation.setGuestEmail(request.getGuestEmail());
        reservation.setNumberOfGuests(request.getNumberOfGuests());
        reservation.setTotalPrice(request.getTotalPrice());
        reservation.setStatus("CONFIRMED");
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationResponse.fromEntity(savedReservation);
    }

    public ReservationResponse getReservationById(String id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));
        return ReservationResponse.fromEntity(reservation);
    }

    public List<ReservationResponse> getReservationsByUserId(String userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations.stream()
                .map(ReservationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public ReservationResponse cancelReservation(String id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));

        reservation.setStatus("CANCELLED");
        reservation.setUpdatedAt(LocalDateTime.now());

        Reservation updatedReservation = reservationRepository.save(reservation);
        return ReservationResponse.fromEntity(updatedReservation);
    }
}
