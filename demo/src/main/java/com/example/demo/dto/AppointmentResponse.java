package com.example.demo.dto;

import java.util.Date;

public class AppointmentResponse {

    private Date time;
    private int duration;
    private String userName;
    private String userSurname;

    public AppointmentResponse() {
    }

    public AppointmentResponse(Date time, int duration, String userName, String userSurname) {
        this.time = time;
        this.duration = duration;
        this.userName = userName;
        this.userSurname = userSurname;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
}
