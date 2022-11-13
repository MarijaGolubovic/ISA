package com.example.demo.dto;

import com.example.demo.model.Address;

public class BloodBankRegistrationRequest {

    private String name;
    private String description;
    private Address address;

    public BloodBankRegistrationRequest() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
