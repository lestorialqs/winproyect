package com.dbp.winproyect.location.dto;

import lombok.Data;
@Data
public class LocationResponseDto {
    private double latitude;
    private double longitude;
    private String formattedAddress;
    private String message;
    private String status;
    private String googleMapsLink;
    public LocationResponseDto() {}
    public LocationResponseDto(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.googleMapsLink = "https://www.google.com/maps?q=" + latitude + "," + longitude;
    }

    // Constructor, getters y setters
}
