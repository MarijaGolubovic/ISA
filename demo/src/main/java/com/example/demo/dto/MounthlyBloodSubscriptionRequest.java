package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.AmountOfBloodType;

public class MounthlyBloodSubscriptionRequest {

	private String APIKey;
	private LocalDateTime dateAndTimeOfSubscription;
	private List<AmountOfBloodType> bloodTypeAmountPair;
	
	public MounthlyBloodSubscriptionRequest(String aPIKey, LocalDateTime dateAndTimeOfSubscription,
			List<AmountOfBloodType> bloodTypeAmountPair) {
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
	public LocalDateTime getDateAndTimeOfSubscription() {
		return dateAndTimeOfSubscription;
	}
	public void setDateAndTimeOfSubscription(LocalDateTime dateAndTimeOfSubscription) {
		this.dateAndTimeOfSubscription = dateAndTimeOfSubscription;
	}
	public List<AmountOfBloodType> getBloodTypeAmountPair() {
		return bloodTypeAmountPair;
	}
	public void setBloodTypeAmountPair(List<AmountOfBloodType> bloodTypeAmountPair) {
		this.bloodTypeAmountPair = bloodTypeAmountPair;
	}
	
}
