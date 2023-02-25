package com.example.demo.dto;

public class FutureAppointmentsBBDTO {
	private String date;
	private String time;
	private String name;
	private String surname;
	private Long iD;
	private Long useriD;
	
	public Long getUseriD() {
		return useriD;
	}
	public void setUseriD(Long useriD) {
		this.useriD = useriD;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Long getiD() {
		return iD;
	}
	public void setiD(Long iD) {
		this.iD = iD;
	}
	public FutureAppointmentsBBDTO(String date, String time, String name, String surname, Long iD, Long useriD) {
		super();
		this.date = date;
		this.time = time;
		this.name = name;
		this.surname = surname;
		this.iD = iD;
		this.useriD=useriD;
	}
	
	
}
