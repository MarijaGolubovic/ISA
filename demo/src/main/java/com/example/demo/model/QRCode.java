package com.example.demo.model;

import com.example.demo.model.enumerations.AppointmentStatus;

import javax.persistence.*;

@Entity
@Table
public class QRCode {
    @Id
    @SequenceGenerator(
            name = "qr_code_sequence",
            sequenceName = "activation_code_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "qr_code_sequence"
    )
    private Long id;


    private Long userId;
    private Long appointmentId;

    @Enumerated(EnumType.STRING)
    @Column(name="appstatus")
    private AppointmentStatus appointmentStatus;

    public void setId(Long id) {
        this.id = id;
    }


    public QRCode(Long userId, Long appointmentId, AppointmentStatus appointmentStatus) {
        this.userId = userId;
        this.appointmentId = appointmentId;
        this.appointmentStatus = appointmentStatus;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
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

    public QRCode() {

    }


}
