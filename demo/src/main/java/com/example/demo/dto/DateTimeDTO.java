package com.example.demo.dto;

import java.util.Date;

public class DateTimeDTO {
	private Date date;
    private String startTime;
    
	public DateTimeDTO(Date date, String startTime) {
		super();
		this.date = date;
		this.startTime = startTime;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
    
    
}
