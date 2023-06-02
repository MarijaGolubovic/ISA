package com.example.demo.dto;

import com.example.demo.model.enumerations.AppointmentStatus;

import java.util.Date;

public class QRCodeDTO {
    private byte[] image;
    private Date date;
    private AppointmentStatus appointmentStatus;

    public QRCodeDTO(byte[] image, Date date, AppointmentStatus appointmentStatus) {
        this.image = image;
        this.date = date;
        this.appointmentStatus = appointmentStatus;
    }

    public QRCodeDTO() {

    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public byte[] getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }
}
