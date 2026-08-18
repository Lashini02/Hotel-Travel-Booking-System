package com.soc.hotel.repository;

import com.soc.hotel.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
    List<Hotel> findByLocationContainingIgnoreCase(String location);
}
