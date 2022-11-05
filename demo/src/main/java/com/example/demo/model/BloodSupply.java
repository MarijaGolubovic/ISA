package com.example.demo.model;

import com.example.demo.model.enumerations.BloodType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class BloodSupply {
    @Id
    @SequenceGenerator(
            name = "blood_sequence",
            sequenceName = "blood_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "blood_sequence"
    )
    private Long id;
    private BloodType bloodType;
    private Double quantity; //u jedinicama?
    private BloodBank bloodBank;

    public BloodSupply() {
    }

    public BloodSupply(Long id, BloodType bloodType, Double quantity, BloodBank bloodBank) {
        this.id = id;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.bloodBank = bloodBank;
    }

    public BloodSupply(BloodType bloodType, Double quantity, BloodBank bloodBank) {
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.bloodBank = bloodBank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }

    @Override
    public String toString() {
        return "BloodSupply{" +
                "id=" + id +
                ", bloodType=" + bloodType +
                ", quantity=" + quantity +
                ", bloodBank=" + bloodBank +
                '}';
    }
}
