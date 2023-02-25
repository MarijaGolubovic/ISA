package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.dto.BloodTypeDTO;

@Entity
@Table
public class AmountOfBloodType {

	@Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
	private Long id;
	private BloodTypeDTO bloodType;
	private int amount;
	@ManyToOne(cascade = CascadeType.ALL)
	private MounthlyBloodSubscription bloodSub;

	public AmountOfBloodType(Long id, BloodTypeDTO bloodType, int amount) {
		super();
		this.id = id;
		this.bloodType = bloodType;
		this.amount = amount;
	}
	public AmountOfBloodType(BloodTypeDTO bloodType, int amount) {
		super();
		this.bloodType = bloodType;
		this.amount = amount;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public AmountOfBloodType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BloodTypeDTO getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodTypeDTO bloodType) {
		this.bloodType = bloodType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public MounthlyBloodSubscription getBloodSub() {
		return bloodSub;
	}
	public void setBloodSub(MounthlyBloodSubscription bloodSub) {
		this.bloodSub = bloodSub;
	}
	
	
}
