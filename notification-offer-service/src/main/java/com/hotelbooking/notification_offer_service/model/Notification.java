package com.hotelbooking.notification_offer_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;
    private String userId;
    private String userEmail;
    private String bookingId;
    private String hotelName;
    private String message;
    private String type;

    public Notification() {}

    public Notification(String userId, String userEmail, String bookingId, String hotelName, String message, String type) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.bookingId = bookingId;
        this.hotelName = hotelName;
        this.message = message;
        this.type = type;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}