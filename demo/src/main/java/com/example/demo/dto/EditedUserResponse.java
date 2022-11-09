package com.example.demo.dto;

import com.example.demo.model.Address;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserType;

public class EditedUserResponse {
	private Long id;
	private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Address address;
    private String URN;
    private Gender gender;
    private String profession;
    private String infoAboutInstitution;
    private UserType userType;
    private int pointsNum;
    
    public EditedUserResponse() {}
    
    
	public String getURN() {
		return URN;
	}


	public void setURN(String uRN) {
		URN = uRN;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getInfoAboutInstitution() {
		return infoAboutInstitution;
	}
	public void setInfoAboutInstitution(String infoAboutInstitution) {
		this.infoAboutInstitution = infoAboutInstitution;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public int getPointsNum() {
		return pointsNum;
	}
	public void setPointsNum(int pointsNum) {
		this.pointsNum = pointsNum;
	}
    
    
}
