package com.soc.hotel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "hotels")
public class Hotel {

    @Id
    private String id;
    private String name;
    private String description;
    private String location;
    private String address;
    private Double rating;
    private List<String> amenities = new ArrayList<>();
    private String contactEmail;
    private String contactPhone;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Hotel() {
    }

    public Hotel(String id, String name, String description, String location, String address, Double rating, List<String> amenities, String contactEmail, String contactPhone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.address = address;
        this.rating = rating;
        this.amenities = amenities;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
