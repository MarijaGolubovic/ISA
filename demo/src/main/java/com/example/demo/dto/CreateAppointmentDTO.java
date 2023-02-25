package com.example.demo.dto;

import java.sql.Time;
import java.util.Date;

import com.example.demo.model.BloodBank;
import com.example.demo.model.enumerations.AppointmentStatus;

public class CreateAppointmentDTO {

	private Long bloodBankID;
    private Date date;
    private String time;
    private int duration;
    private AppointmentStatus status;
	
    public CreateAppointmentDTO(Long bloodBankID, Date date, String time, int duration, AppointmentStatus status) {
		super();
		this.bloodBankID = bloodBankID;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.status = status;
	}

	public CreateAppointmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getBloodBankID() {
		return bloodBankID;
	}

	public void setBloodBankID(Long bloodBankID) {
		this.bloodBankID = bloodBankID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
    
	
}
