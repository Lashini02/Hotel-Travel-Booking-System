package com.hotelbooking.reservation.repository;

import com.hotelbooking.reservation.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    List<Reservation> findByUserId(String userId);

    List<Reservation> findByHotelId(String hotelId);
}
