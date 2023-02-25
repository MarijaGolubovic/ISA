package com.example.demo.dto;

public class FutureAppointmentDTO {

	private String bloodBankName;
	private String date;
	private String time;
	
	public FutureAppointmentDTO(String bloodBankName, String date, String time) {
		super();
		this.bloodBankName = bloodBankName;
		this.date = date;
		this.time = time;
	}
	public FutureAppointmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBloodBankName() {
		return bloodBankName;
	}
	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
