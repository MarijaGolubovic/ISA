package com.example.demo.model;

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
    private String bloodType;
    private Double quantity;

    public BloodSupply(){
    }

    public BloodSupply(Long id, String bloodType, Double quantity) {
        this.id = id;
        this.bloodType = bloodType;
        this.quantity = quantity;
    }

    public BloodSupply(String bloodType, Double quantity) {
        this.bloodType = bloodType;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BloodSupply{" +
                "id=" + id +
                ", bloodType='" + bloodType + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
