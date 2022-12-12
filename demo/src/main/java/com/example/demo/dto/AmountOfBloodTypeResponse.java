package com.example.demo.dto;


public class AmountOfBloodTypeResponse {

	private BloodType bloodType;
	private int amount;
	public AmountOfBloodTypeResponse(BloodType bloodType, int amount) {
		super();
		this.bloodType = bloodType;
		this.amount = amount;
	}
	public AmountOfBloodTypeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BloodType getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
