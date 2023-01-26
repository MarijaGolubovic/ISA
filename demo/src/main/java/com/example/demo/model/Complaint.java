package com.example.demo.model;

import com.example.demo.model.enumerations.ComplaintStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Complaint {
    @Id
    @SequenceGenerator(
            name = "complaint_sequence",
            sequenceName = "complaint_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "complaint_sequence"
    )
    private Long id;
    @OneToOne
    @JoinColumn(name = "bb_id",referencedColumnName = "id")
    private BloodBank bloodBank;
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    private String description;
    private Date date;
    private ComplaintStatus status;
    private String reply;
    @Version
    private Integer version;
    public Complaint() {
    }

    public Complaint(Long id, BloodBank bloodBank, User user, String description, Date date, ComplaintStatus status, String reply) {
        this.id = id;
        this.bloodBank = bloodBank;
        this.user = user;
        this.description = description;
        this.date = date;
        this.status = status;
        this.reply = reply;
    }

    public Complaint(BloodBank bloodBank, User user, String description, Date date, ComplaintStatus status, String reply) {
        this.bloodBank = bloodBank;
        this.user = user;
        this.description = description;
        this.date = date;
        this.status = status;
        this.reply = reply;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }


}
