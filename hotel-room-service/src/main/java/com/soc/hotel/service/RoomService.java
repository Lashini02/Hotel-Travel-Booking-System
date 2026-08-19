package com.soc.hotel.service;

import com.soc.hotel.exception.ResourceNotFoundException;
import com.soc.hotel.model.Room;
import com.soc.hotel.repository.HotelRepository;
import com.soc.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<Room> getRoomsByHotelId(String hotelId, Boolean availableOnly) {
        // Verify hotel exists
        if (!hotelRepository.existsById(hotelId)) {
            throw new ResourceNotFoundException("Hotel not found with ID: " + hotelId);
        }

        if (availableOnly != null && availableOnly) {
            return roomRepository.findByHotelIdAndAvailable(hotelId, true);
        }
        return roomRepository.findByHotelId(hotelId);
    }

    public Room getRoomById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + id));
    }

    public Room createRoom(String hotelId, Room room) {
        if (!hotelRepository.existsById(hotelId)) {
            throw new ResourceNotFoundException("Cannot create room. Hotel not found with ID: " + hotelId);
        }
        room.setId(null);
        room.setHotelId(hotelId);
        if (room.getAvailable() == null) {
            room.setAvailable(true);
        }
        return roomRepository.save(room);
    }

    public Room updateRoomAvailability(String roomId, Boolean available) {
        Room room = getRoomById(roomId);
        room.setAvailable(available);
        return roomRepository.save(room);
    }

    public void deleteRoom(String id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }
}
