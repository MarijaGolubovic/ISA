package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class CanceledApointments {
    @Id
    @SequenceGenerator(
            name = "canceled_appointments_sequence",
            sequenceName = "canceled_appointments_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "canceled_appointments_sequence"
    )
    private Long id;
    private Long userId;
    private Long appointmentId;
    private Date cancelingDate;

    public CanceledApointments(){}

    public CanceledApointments(Long userId, Long appointmentId, Date cancelingDate) {
        this.userId = userId;
        this.appointmentId = appointmentId;
        this.cancelingDate = cancelingDate;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public Date getCancelingDate() {
        return cancelingDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setCancelingDate(Date cancelingDate) {
        this.cancelingDate = cancelingDate;
    }
}
