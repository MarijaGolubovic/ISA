package com.example.demo.model;

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
    private BloodBank bloodBank;
    private User user;
    private String description;
    private Date date;

    public Complaint() {
    }

    public Complaint(Long id, BloodBank bloodBank, User user, String description, Date date) {
        this.id = id;
        this.bloodBank = bloodBank;
        this.user = user;
        this.description = description;
        this.date = date;
    }

    public Complaint(BloodBank bloodBank, User user, String description, Date date) {
        this.bloodBank = bloodBank;
        this.user = user;
        this.description = description;
        this.date = date;
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

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", bloodBank=" + bloodBank +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
