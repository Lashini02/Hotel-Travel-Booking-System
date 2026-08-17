package com.soc.hotel.controller;

import com.soc.hotel.dto.ApiResponse;
import com.soc.hotel.dto.AvailabilityUpdateRequest;
import com.soc.hotel.model.Room;
import com.soc.hotel.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Room Controller", description = "Endpoints for managing rooms, pricing, and real-time availability")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/hotels/{hotelId}/rooms")
    @Operation(summary = "Get rooms for a hotel", description = "Retrieve all rooms for a specific hotel ID, optionally filtering available ones.")
    public ResponseEntity<ApiResponse<List<Room>>> getRoomsByHotelId(
            @PathVariable String hotelId,
            @RequestParam(required = false) Boolean availableOnly) {
        List<Room> rooms = roomService.getRoomsByHotelId(hotelId, availableOnly);
        return ResponseEntity.ok(ApiResponse.success("Rooms retrieved successfully", rooms));
    }

    @PostMapping("/hotels/{hotelId}/rooms")
    @Operation(summary = "Add room to hotel", description = "Create a new room under a specific hotel ID.")
    public ResponseEntity<ApiResponse<Room>> createRoom(
            @PathVariable String hotelId,
            @Valid @RequestBody Room room) {
        Room createdRoom = roomService.createRoom(hotelId, room);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Room created successfully", createdRoom));
    }

    @GetMapping("/rooms/{id}")
    @Operation(summary = "Get room by ID", description = "Retrieve room details by room ID.")
    public ResponseEntity<ApiResponse<Room>> getRoomById(@PathVariable String id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(ApiResponse.success("Room retrieved successfully", room));
    }

    @PutMapping("/rooms/{id}/availability")
    @Operation(summary = "Update room availability status", description = "Update the real-time availability status (true/false) of a specific room.")
    public ResponseEntity<ApiResponse<Room>> updateRoomAvailability(
            @PathVariable String id,
            @Valid @RequestBody AvailabilityUpdateRequest request) {
        Room updatedRoom = roomService.updateRoomAvailability(id, request.getAvailable());
        return ResponseEntity.ok(ApiResponse.success("Room availability updated successfully", updatedRoom));
    }

    @DeleteMapping("/rooms/{id}")
    @Operation(summary = "Delete room", description = "Remove a room by ID.")
    public ResponseEntity<ApiResponse<Void>> deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(ApiResponse.success("Room deleted successfully", null));
    }
}
