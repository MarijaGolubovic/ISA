package com.example.demo.dto;


public class AmountOfBloodTypeResponse {

	private BloodTypeDTO bloodType;
	private int amount;
	public AmountOfBloodTypeResponse(BloodTypeDTO bloodType, int amount) {
		super();
		this.bloodType = bloodType;
		this.amount = amount;
	}
	public AmountOfBloodTypeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BloodTypeDTO getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodTypeDTO bloodType) {
		this.bloodType = bloodType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
