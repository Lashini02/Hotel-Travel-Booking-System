package com.hotelbooking.notification_offer_service.controller;

import com.hotelbooking.notification_offer_service.dto.BookingNotificationRequest;
import com.hotelbooking.notification_offer_service.dto.PromoValidationRequest;
import com.hotelbooking.notification_offer_service.model.Notification;
import com.hotelbooking.notification_offer_service.repository.NotificationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification & Offer Service", description = "Endpoints for confirmations, promo validation, and offer subscriptions")
public class NotificationOfferController {

    private final NotificationRepository notificationRepository;

    // Constructor Injection
    public NotificationOfferController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Endpoint 1: Send Booking Confirmation & Save to MongoDB
    @PostMapping("/confirm-booking")
    @Operation(summary = "Send booking confirmation email/SMS and store in DB")
    public ResponseEntity<Map<String, String>> sendBookingConfirmation(@RequestBody BookingNotificationRequest request) {
        String msg = "Confirmation sent to " + request.userEmail() + " for booking " + request.bookingId() + " at " + request.hotelName();
        
        // Save record to MongoDB
        Notification notification = new Notification(
            request.userId(),
            request.userEmail(),
            request.bookingId(),
            request.hotelName(),
            msg,
            "BOOKING_CONFIRMATION"
        );
        notificationRepository.save(notification);

        return ResponseEntity.ok(Map.of("status", "SUCCESS", "message", msg));
    }

    // Endpoint 2: Validate Promo Code
    @PostMapping("/offers/validate-promo")
    @Operation(summary = "Validate promo code and calculate discounted price")
    public ResponseEntity<Map<String, Object>> validatePromo(@RequestBody PromoValidationRequest request) {
        if ("SUMMER2026".equalsIgnoreCase(request.promoCode())) {
            double discount = request.originalAmount() * 0.20;
            return ResponseEntity.ok(Map.of("valid", true, "discountAmount", discount, "finalPrice", request.originalAmount() - discount));
        }
        return ResponseEntity.ok(Map.of("valid", false, "discountAmount", 0.0, "finalPrice", request.originalAmount()));
    }

    // Endpoint 3: Fetch Notification History from MongoDB
    @GetMapping("/history/{userId}")
    @Operation(summary = "Get notification history for a user from database")
    public ResponseEntity<List<Notification>> getNotificationHistory(@PathVariable String userId) {
        List<Notification> history = notificationRepository.findByUserId(userId);
        return ResponseEntity.ok(history);
    }

    // Endpoint 4: Subscribe User to Promotional Offers
    @PostMapping("/offers/subscribe")
    @Operation(summary = "Subscribe user to discount alerts")
    public ResponseEntity<Map<String, String>> subscribeToOffers(@RequestParam String email) {
        Notification notification = new Notification(
            "GUEST",
            email,
            "N/A",
            "N/A",
            "User subscribed to promotional offers",
            "PROMO_SUBSCRIBE"
        );
        notificationRepository.save(notification);

        return ResponseEntity.ok(Map.of("status", "SUBSCRIBED", "message", email + " successfully subscribed to promotional offers."));
    }
}