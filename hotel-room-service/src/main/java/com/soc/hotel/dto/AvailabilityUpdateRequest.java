package com.soc.hotel.dto;

import jakarta.validation.constraints.NotNull;

public class AvailabilityUpdateRequest {

    @NotNull(message = "Available status is required (true/false)")
    private Boolean available;

    public AvailabilityUpdateRequest() {
    }

    public AvailabilityUpdateRequest(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
