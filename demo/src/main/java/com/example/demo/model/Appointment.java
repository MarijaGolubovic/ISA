package com.example.demo.model;

import com.example.demo.model.enumerations.AppointmentStatus;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_sequence"
    )
    private Long id;
    private BloodBank bloodBank;
    private Date date;
    private Time time;
    private int duration; //in minutes
    private List<User> medicalStuff;
    private AppointmentStatus status;

    public Appointment() {
    }

    public Appointment(Long id, BloodBank bloodBank, Date date, Time time, int duration, List<User> medicalStuff, AppointmentStatus status) {
        this.id = id;
        this.bloodBank = bloodBank;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.medicalStuff = medicalStuff;
        this.status = status;
    }

    public Appointment(BloodBank bloodBank, Date date, Time time, int duration, List<User> medicalStuff, AppointmentStatus status) {
        this.bloodBank = bloodBank;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.medicalStuff = medicalStuff;
        this.status = status;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<User> getMedicalStuff() {
        return medicalStuff;
    }

    public void setMedicalStuff(List<User> medicalStuff) {
        this.medicalStuff = medicalStuff;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", bloodBank=" + bloodBank +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", medicalStuff=" + medicalStuff +
                ", status=" + status +
                '}';
    }
}
