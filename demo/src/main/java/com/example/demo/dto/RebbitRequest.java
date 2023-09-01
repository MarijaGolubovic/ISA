package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RebbitRequest {
    @JsonProperty("Latitude")
    private double latitude;

    @JsonProperty("Longitude")
    private double longitude;

    @JsonProperty("BloodUnits")
    private int bloodUnits;

    @JsonProperty("Status")
    private String status;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getBloodUnits() {
        return bloodUnits;
    }

    public String getStatus() {
        return status;
    }
}
