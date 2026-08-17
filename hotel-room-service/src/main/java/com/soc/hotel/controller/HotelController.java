package com.soc.hotel.controller;

import com.soc.hotel.dto.ApiResponse;
import com.soc.hotel.model.Hotel;
import com.soc.hotel.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
@Tag(name = "Hotel Controller", description = "Endpoints for managing hotel listings and details")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    @Operation(summary = "Get all hotels", description = "Retrieve a list of all hotels, optionally filtered by location.")
    public ResponseEntity<ApiResponse<List<Hotel>>> getAllHotels(
            @RequestParam(required = false) String location) {
        List<Hotel> hotels = hotelService.getAllHotels(location);
        return ResponseEntity.ok(ApiResponse.success("Hotels retrieved successfully", hotels));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get hotel by ID", description = "Retrieve detailed information for a specific hotel by ID.")
    public ResponseEntity<ApiResponse<Hotel>> getHotelById(@PathVariable String id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(ApiResponse.success("Hotel details retrieved successfully", hotel));
    }

    @PostMapping
    @Operation(summary = "Create hotel (Admin)", description = "Add a new hotel listing to the system.")
    public ResponseEntity<ApiResponse<Hotel>> createHotel(@Valid @RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Hotel created successfully", createdHotel));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update hotel", description = "Update existing hotel details by ID.")
    public ResponseEntity<ApiResponse<Hotel>> updateHotel(
            @PathVariable String id,
            @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        return ResponseEntity.ok(ApiResponse.success("Hotel updated successfully", updatedHotel));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete hotel", description = "Remove a hotel listing by ID.")
    public ResponseEntity<ApiResponse<Void>> deleteHotel(@PathVariable String id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok(ApiResponse.success("Hotel deleted successfully", null));
    }
}
