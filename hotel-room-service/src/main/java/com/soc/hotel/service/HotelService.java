package com.soc.hotel.service;

import com.soc.hotel.exception.ResourceNotFoundException;
import com.soc.hotel.model.Hotel;
import com.soc.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels(String location) {
        if (location != null && !location.trim().isEmpty()) {
            return hotelRepository.findByLocationContainingIgnoreCase(location.trim());
        }
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + id));
    }

    public Hotel createHotel(Hotel hotel) {
        hotel.setId(null); // Ensure MongoDB generates new ID
        hotel.setCreatedAt(LocalDateTime.now());
        if (hotel.getRating() == null) {
            hotel.setRating(4.5);
        }
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(String id, Hotel updatedHotel) {
        Hotel existingHotel = getHotelById(id);
        
        if (updatedHotel.getName() != null) existingHotel.setName(updatedHotel.getName());
        if (updatedHotel.getDescription() != null) existingHotel.setDescription(updatedHotel.getDescription());
        if (updatedHotel.getLocation() != null) existingHotel.setLocation(updatedHotel.getLocation());
        if (updatedHotel.getAddress() != null) existingHotel.setAddress(updatedHotel.getAddress());
        if (updatedHotel.getRating() != null) existingHotel.setRating(updatedHotel.getRating());
        if (updatedHotel.getAmenities() != null) existingHotel.setAmenities(updatedHotel.getAmenities());
        if (updatedHotel.getContactEmail() != null) existingHotel.setContactEmail(updatedHotel.getContactEmail());
        if (updatedHotel.getContactPhone() != null) existingHotel.setContactPhone(updatedHotel.getContactPhone());

        return hotelRepository.save(existingHotel);
    }

    public void deleteHotel(String id) {
        Hotel hotel = getHotelById(id);
        hotelRepository.delete(hotel);
    }
}
