package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.AmountOfBloodType;

public class MounthlyBloodSubscriptionResponse {

	private String messageForManager;
	private String APIKey;
	private List<AmountOfBloodTypeResponse> bloodTypeAmountPair;
	
	public MounthlyBloodSubscriptionResponse(String messageForManager, String aPIKey,
			List<AmountOfBloodTypeResponse> amountsOfBloodTypes) {
		super();
		this.messageForManager = messageForManager;
		APIKey = aPIKey;
		this.bloodTypeAmountPair = amountsOfBloodTypes;
	}
	public MounthlyBloodSubscriptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMessageForManager() {
		return messageForManager;
	}
	public void setMessageForManager(String messageForManager) {
		this.messageForManager = messageForManager;
	}
	public String getAPIKey() {
		return APIKey;
	}
	public void setAPIKey(String aPIKey) {
		APIKey = aPIKey;
	}
	public List<AmountOfBloodTypeResponse> getAmountsOfBloodTypes() {
		return bloodTypeAmountPair;
	}
	public void setAmountsOfBloodTypes(List<AmountOfBloodTypeResponse> amountsOfBloodTypes) {
		this.bloodTypeAmountPair = amountsOfBloodTypes;
	}
	
	
	
}
