package com.soc.auth.controller;

import com.soc.auth.dto.*;
import com.soc.auth.model.User;
import com.soc.auth.repository.UserRepository;
import com.soc.auth.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication & Gateway API", description = "Endpoints for User Registration, Login, Token Issuance, and Token Validation")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account, encrypts password, generates API key, and returns JWT token.")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getUsername() == null || registerRequest.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is required!"));
        }
        if (registerRequest.getEmail() == null || registerRequest.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is required!"));
        }
        if (registerRequest.getPassword() == null || registerRequest.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Password is required!"));
        }

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Generate unique API key for user
        String userApiKey = "KEY-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();

        User user = new User(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getFullName(),
                registerRequest.getPhone(),
                registerRequest.getAddress(),
                "USER",
                userApiKey
        );

        User savedUser = userRepository.save(user);

        // Generate JWT Token
        String token = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole(), savedUser.getId());

        AuthResponse authResponse = new AuthResponse(
                token,
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getApiKey()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user & issue JWT token", description = "Validates user credentials and returns an OAuth 2.0 / JWT token and API Key.")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getEmailOrUsername() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Credentials required!"));
        }

        String credential = loginRequest.getEmailOrUsername().trim();
        Optional<User> userOptional = userRepository.findByUsername(credential);
        if (userOptional.isEmpty()) {
            userOptional = userRepository.findByEmail(credential);
        }

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Error: Invalid username/email or password!"));
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Error: Invalid username/email or password!"));
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getEmail(), user.getRole(), user.getId());

        AuthResponse authResponse = new AuthResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getApiKey()
        );

        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/validate")
    @Operation(summary = "Validate JWT Token", description = "Used by API Gateway and downstream microservices to verify token validity.")
    public ResponseEntity<?> validateToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Invalid or missing Authorization header"));
        }

        String token = authHeader.substring(7);
        if (jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUsernameFromToken(token);
            return ResponseEntity.ok(new MessageResponse("Token is valid for user: " + username));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Token is invalid or expired"));
        }
    }
}
