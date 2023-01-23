package com.example.demo.model;

import javax.persistence.*;

import com.example.demo.model.enumerations.BloodType;

@Entity
@Table
public class Survey {
    @Id
    @SequenceGenerator(
            name = "survey_sequence",
            sequenceName = "survey_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "survey_sequence"
    )
    private Long id;      
    private BloodType bloodType; 
    private String generalCondition;
    private Integer systolicBP;
    private Integer diastolicBP;
    private Integer pulse;
    private Integer usedBags;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BloodType getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}
	public String getGeneralCondition() {
		return generalCondition;
	}
	public void setGeneralCondition(String generalCondition) {
		this.generalCondition = generalCondition;
	}
	public Integer getSystolicBP() {
		return systolicBP;
	}
	public void setSystolicBP(Integer systolicBP) {
		this.systolicBP = systolicBP;
	}
	public Integer getDiastolicBP() {
		return diastolicBP;
	}
	public void setDiastolicBP(Integer diastolicBP) {
		this.diastolicBP = diastolicBP;
	}
	public Integer getPulse() {
		return pulse;
	}
	public void setPulse(Integer pulse) {
		this.pulse = pulse;
	}
	public Integer getUsedBags() {
		return usedBags;
	}
	public void setUsedBags(Integer usedBags) {
		this.usedBags = usedBags;
	}
	public Survey(Long id, BloodType bloodType, String generalCondition, Integer systolicBP, Integer diastolicBP,
			Integer pulse, Integer usedBags) {
		super();
		this.id = id;
		this.bloodType = bloodType;
		this.generalCondition = generalCondition;
		this.systolicBP = systolicBP;
		this.diastolicBP = diastolicBP;
		this.pulse = pulse;
		this.usedBags = usedBags;
	}
	public Survey(BloodType bloodType, String generalCondition, Integer systolicBP, Integer diastolicBP, Integer pulse,
			Integer usedBags) {
		super();
		this.bloodType = bloodType;
		this.generalCondition = generalCondition;
		this.systolicBP = systolicBP;
		this.diastolicBP = diastolicBP;
		this.pulse = pulse;
		this.usedBags = usedBags;
	}
	public Survey() {

	}

    
    
    
}
