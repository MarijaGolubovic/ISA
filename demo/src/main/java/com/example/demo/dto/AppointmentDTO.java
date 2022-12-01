package com.example.demo.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.example.demo.model.BloodBank;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.AppointmentStatus;

public class AppointmentDTO {
	
	private Long id;
    private Date date;
    private LocalTime time;
    private int duration; //in minutes
    private AppointmentStatus status;
    private List<User> medicalStuff;
    
	public List<User> getMedicalStuff() {
		return medicalStuff;
	}
	public void setMedicalStuff(List<User> medicalStuff) {
		this.medicalStuff = medicalStuff;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
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
	public AppointmentDTO(Long id, Date date, LocalTime time, int duration, AppointmentStatus status,
			List<User> medicalStuff) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.status = status;
		this.medicalStuff = medicalStuff;
	}
	public AppointmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
    
}
