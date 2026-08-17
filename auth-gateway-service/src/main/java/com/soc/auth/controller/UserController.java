package com.soc.auth.controller;

import com.soc.auth.dto.MessageResponse;
import com.soc.auth.dto.UserProfileUpdateRequest;
import com.soc.auth.model.User;
import com.soc.auth.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "User Profile Management API", description = "Endpoints for retrieving and updating user profile information.")
@SecurityRequirement(name = "bearerAuth")
@SecurityRequirement(name = "apiKeyHeader")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    @Operation(summary = "Get user profile", description = "Returns profile information for the authenticated user.")
    public ResponseEntity<?> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(new MessageResponse("Unauthorized request"));
        }

        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("User not found"));
        }

        User user = userOptional.get();
        // Hide password in response
        user.setPassword(null);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    @Operation(summary = "Update user profile", description = "Updates profile details (fullName, phone, address) for the authenticated user.")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileUpdateRequest updateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(new MessageResponse("Unauthorized request"));
        }

        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse("User not found"));
        }

        User user = userOptional.get();

        if (updateRequest.getFullName() != null) {
            user.setFullName(updateRequest.getFullName());
        }
        if (updateRequest.getPhone() != null) {
            user.setPhone(updateRequest.getPhone());
        }
        if (updateRequest.getAddress() != null) {
            user.setAddress(updateRequest.getAddress());
        }

        User updatedUser = userRepository.save(user);
        updatedUser.setPassword(null);

        return ResponseEntity.ok(updatedUser);
    }
}
