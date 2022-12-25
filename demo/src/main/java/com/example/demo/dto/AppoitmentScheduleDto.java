package com.example.demo.dto;

import com.example.demo.model.User;
import com.example.demo.model.enumerations.AppointmentStatus;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AppoitmentScheduleDto {

    private String EventTitle;
    private Date EventStart;
    private Date EventEnd;

    public AppoitmentScheduleDto() {

    }

    public AppoitmentScheduleDto(String eventTitle, Date eventStart, Date eventEnd) {
        EventTitle = eventTitle;
        EventStart = eventStart;
        EventEnd = eventEnd;
    }

    public String getEventTitle() {
        return EventTitle;
    }

    public void setEventTitle(String eventTitle) {
        EventTitle = eventTitle;
    }

    public Date getEventStart() {
        return EventStart;
    }

    public void setEventStart(Date eventStart) {
        EventStart = eventStart;
    }

    public Date getEventEnd() {
        return EventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        EventEnd = eventEnd;
    }
}
