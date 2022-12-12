package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

@Entity
@Table
public class MounthlyBloodSubscription {

	@Id
    @SequenceGenerator(
            name = "bloodbank_sequence",
            sequenceName = "bloodbank_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bloodbank_sequence"
    )
	private Long id;
	@OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "bloodBank_id",referencedColumnName = "id")
	private BloodBank bloodBank;
	private String APIKey;
	private Date dateAndTimeOfSubscription;
	@OneToMany(mappedBy = "bloodSub", cascade = CascadeType.ALL)
	private List<AmountOfBloodType> amountOfBloodTypes = new ArrayList<AmountOfBloodType>();
	
	public MounthlyBloodSubscription(Long id, BloodBank bloodBank, String aPIKey,
			Date dateAndTimeOfSubscription, List<AmountOfBloodType> amountOfBloodTypes) {
		super();
		this.id = id;
		this.bloodBank = bloodBank;
		this.APIKey = aPIKey;
		this.dateAndTimeOfSubscription = dateAndTimeOfSubscription;
		this.amountOfBloodTypes = amountOfBloodTypes;
	}
	
	public MounthlyBloodSubscription(BloodBank bloodBank, String aPIKey,
			Date dateAndTimeOfSubscription, List<AmountOfBloodType> amountOfBloodTypes) {
		super();
		this.bloodBank = bloodBank;
		this.APIKey = aPIKey;
		this.dateAndTimeOfSubscription = dateAndTimeOfSubscription;
		this.amountOfBloodTypes = amountOfBloodTypes;
	}
	
	public MounthlyBloodSubscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BloodBank getBloodBank() {
		return bloodBank;
	}
	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
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

	public List<AmountOfBloodType> getAmountOfBloodTypes() {
		return amountOfBloodTypes;
	}

	public void setAmountOfBloodTypes(List<AmountOfBloodType> amountOfBloodTypes) {
		this.amountOfBloodTypes = amountOfBloodTypes;
	}
	
	
}
