package com.example.demo.dto;

import com.example.demo.model.enumerations.BloodType2;

public class AmountOfBloodTypeResponse {

	private BloodType2 bloodType;
	private int amount;
	public AmountOfBloodTypeResponse(BloodType2 bloodType, int amount) {
		super();
		this.bloodType = bloodType;
		this.amount = amount;
	}
	public AmountOfBloodTypeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BloodType2 getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType2 bloodType) {
		this.bloodType = bloodType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
