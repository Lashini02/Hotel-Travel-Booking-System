package com.hotelbooking.notification_offer_service.dto;

public record BookingNotificationRequest(String userId, String userEmail, String bookingId, String hotelName) {}