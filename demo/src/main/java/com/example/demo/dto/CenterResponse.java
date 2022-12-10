package com.example.demo.dto;

import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CenterResponse {
	private Long id;
	private String name;
	private String city;
	private String street;
	private String streetNumber;
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
	
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	
	public CenterResponse(Long id, String name, String city, String street, String streetNumber, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
		this.grade = grade;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CenterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
}