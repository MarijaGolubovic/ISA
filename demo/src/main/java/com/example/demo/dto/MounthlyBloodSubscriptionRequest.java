package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.example.demo.model.AmountOfBloodType;

public class MounthlyBloodSubscriptionRequest {

	private String APIKey;
	private Date dateAndTimeOfSubscription;
	private List<AmountOfBloodTypeResponse> bloodTypeAmountPair;
	
	public MounthlyBloodSubscriptionRequest(String aPIKey, Date dateAndTimeOfSubscription,
			List<AmountOfBloodTypeResponse> bloodTypeAmountPair) {
		super();
		APIKey = aPIKey;
		this.dateAndTimeOfSubscription = dateAndTimeOfSubscription;
		this.bloodTypeAmountPair = bloodTypeAmountPair;
	}
	
	public MounthlyBloodSubscriptionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAPIKey() {
		return APIKey;
	}
	public void setAPIKey(String aPIKey) {
		APIKey = aPIKey;
	}
	public Date getDateAndTimeOfSubscription() {
		return dateAndTimeOfSubscription;
	}
	public void setDateAndTimeOfSubscription(Date dateAndTimeOfSubscription) {
		this.dateAndTimeOfSubscription = dateAndTimeOfSubscription;
	}
	public List<AmountOfBloodTypeResponse> getBloodTypeAmountPair() {
		return bloodTypeAmountPair;
	}
	public void setBloodTypeAmountPair(List<AmountOfBloodTypeResponse> bloodTypeAmountPair) {
		this.bloodTypeAmountPair = bloodTypeAmountPair;
	}
	
}
