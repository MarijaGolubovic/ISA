package com.example.demo.dto;

import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CenterResponse {
	private String name;
	private String city;
	private String street;
	private double grade;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public CenterResponse(String name, String city, String street, double grade) {
		super();
		this.name = name;
		this.city = city;
		this.street = street;
		this.grade = grade;
	}
	public CenterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
}