package com.example.demo.dto;

import com.example.demo.model.enumerations.BloodType;

public class BloodSupplyDTO {
	private Long id;
    private BloodType bloodType;
    private Double quantity; 
    private Long bloodBankID;
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
	public Long getBloodBankID() {
		return bloodBankID;
	}
	public void setBloodBankID(Long bloodBankID) {
		this.bloodBankID = bloodBankID;
	}
	public BloodSupplyDTO(Long id, BloodType bloodType, Double quantity, Long bloodBankID) {
		super();
		this.id = id;
		this.bloodType = bloodType;
		this.quantity = quantity;
		this.bloodBankID = bloodBankID;
	}
    
    
}
