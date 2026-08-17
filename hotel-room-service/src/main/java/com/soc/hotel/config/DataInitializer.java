package com.soc.hotel.config;

import com.soc.hotel.model.Hotel;
import com.soc.hotel.model.Room;
import com.soc.hotel.repository.HotelRepository;
import com.soc.hotel.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public DataInitializer(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only populate if database is empty
        if (hotelRepository.count() == 0) {
            System.out.println(">>> Initializing Sample Data for Hotel & Room Service...");

            Hotel h1 = hotelRepository.save(new Hotel(
                    null,
                    "Grand Palace Hotel & Spa",
                    "Luxury 5-star oceanfront resort with full spa services and fine dining.",
                    "Colombo",
                    "100 Galle Face Green, Colombo 03",
                    4.8,
                    List.of("WiFi", "Swimming Pool", "Spa", "Fitness Center", "Ocean View"),
                    "info@grandpalace.com",
                    "+94112345678"
            ));

            Hotel h2 = hotelRepository.save(new Hotel(
                    null,
                    "Hill Country Heritage Villa",
                    "Peaceful mountain retreat nestled in tea plantations with panoramic views.",
                    "Nuwara Eliya",
                    "45 Upper Lake Road, Nuwara Eliya",
                    4.6,
                    List.of("WiFi", "Fireplace", "Garden", "Breakfast Included", "Mountain View"),
                    "stay@hillheritage.com",
                    "+94522234567"
            ));

            Hotel h3 = hotelRepository.save(new Hotel(
                    null,
                    "Southern Beach Palms Resort",
                    "Tropical beachside resort perfect for surfing and whale watching.",
                    "Mirissa",
                    "12 Beach Road, Mirissa",
                    4.5,
                    List.of("WiFi", "Beach Access", "Bar", "Water Sports", "Airport Shuttle"),
                    "contact@beachpalms.com",
                    "+94412234567"
            ));

            // Seed Rooms for Hotel 1
            roomRepository.save(new Room(
                    null,
                    h1.getId(),
                    "101",
                    "DELUXE",
                    150.00,
                    2,
                    true,
                    "Deluxe Double Room with private balcony overlooking the ocean.",
                    List.of("King Bed", "Air Conditioning", "Sea View", "Minibar", "TV")
            ));

            roomRepository.save(new Room(
                    null,
                    h1.getId(),
                    "201",
                    "SUITE",
                    320.00,
                    4,
                    true,
                    "Executive Presidential Suite with separate living room and jacuzzi.",
                    List.of("2 King Beds", "Jacuzzi", "Ocean View", "Private Lounge Access")
            ));

            // Seed Rooms for Hotel 2
            roomRepository.save(new Room(
                    null,
                    h2.getId(),
                    "V1",
                    "DOUBLE",
                    90.00,
                    2,
                    true,
                    "Cozy wooden cabin room with fireplace and garden view.",
                    List.of("Queen Bed", "Fireplace", "Heater", "Mountain View")
            ));

            roomRepository.save(new Room(
                    null,
                    h2.getId(),
                    "V2",
                    "SINGLE",
                    60.00,
                    1,
                    false, // Currently booked / unavailable
                    "Single traveller room with tea garden views.",
                    List.of("Single Bed", "Heater", "Free Tea Service")
            ));

            // Seed Rooms for Hotel 3
            roomRepository.save(new Room(
                    null,
                    h3.getId(),
                    "B10",
                    "DELUXE",
                    110.00,
                    3,
                    true,
                    "Beachfront triple bed room right next to the surf point.",
                    List.of("1 Double Bed", "1 Single Bed", "Air Conditioning", "Balcony")
            ));

            System.out.println(">>> Sample Data Initialized Successfully!");
        }
    }
}
