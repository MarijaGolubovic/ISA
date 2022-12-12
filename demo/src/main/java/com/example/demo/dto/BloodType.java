package com.example.demo.dto;

import com.google.gson.annotations.SerializedName;

public enum BloodType {
	@SerializedName("0")
	Aneg(0),
	@SerializedName("1")
	Apos(1),
	@SerializedName("2")
	Bneg(2),
	@SerializedName("3")
	Bpos(3),
	@SerializedName("4")
	ABpos(4),
	@SerializedName("5")
	ABneg(5),
	@SerializedName("6")
	Opos(6),
	@SerializedName("7")
	Oneg(7);
	
	private final int value;
	
    public int getValue() {
        return value;
    }

    private BloodType(int value) {
        this.value = value;
    }
}

