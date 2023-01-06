package com.example.demo.model;



import javax.persistence.*;

import com.example.demo.dto.BloodTypeDTO;

@Entity
@Table(name="bloodsupply")
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
    @Enumerated(EnumType.STRING)
    private BloodTypeDTO bloodType;
    private Double quantity; //u jedinicama?
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "bb_id",referencedColumnName = "id")
    private BloodBank bloodBank;

    public BloodSupply() {
    }

    public BloodSupply(Long id, BloodTypeDTO bloodType, Double quantity, BloodBank bloodBank) {
        this.id = id;
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.bloodBank = bloodBank;
    }

    public BloodSupply(BloodTypeDTO bloodType, Double quantity, BloodBank bloodBank) {
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

    public BloodTypeDTO getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodTypeDTO bloodType) {
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
