package com.example.demo.dto;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class PenalsNumberDTO {
    private int penalNum;
    private Date date;
    private LocalTime time;
    private int duration;
    private  String bloodBankName;
    private Date cancekingTime;

    public PenalsNumberDTO(int penalNum, Date date, LocalTime time, int duration, String bloodBankName, Date cancekingTime) {
        this.penalNum = penalNum;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.bloodBankName = bloodBankName;
        this.cancekingTime = cancekingTime;
    }

    public PenalsNumberDTO() {

    }

    public void setPenalNum(int penalNum) {
        this.penalNum = penalNum;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setBloodBankName(String bloodBankName) {
        this.bloodBankName = bloodBankName;
    }

    public void setCancekingTime(Date cancekingTime) {
        this.cancekingTime = cancekingTime;
    }

    public int getPenalNum() {
        return penalNum;
    }

    public Date getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public String getBloodBankName() {
        return bloodBankName;
    }

    public Date getCancekingTime() {
        return cancekingTime;
    }
}
