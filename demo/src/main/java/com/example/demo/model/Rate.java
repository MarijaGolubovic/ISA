package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Rate {
    @Id
    @SequenceGenerator(
            name = "rate_sequence",
            sequenceName = "rate_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rate_sequence"
    )
    private Long id;
    @OneToOne
    @JoinColumn(name = "bb_id",referencedColumnName = "id")
    private BloodBank bloodBank;
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    private int rate;

    public Rate() {
    }

    public Rate(Long id, BloodBank bloodBank, User user, int rate) {
        this.id = id;
        this.bloodBank = bloodBank;
        this.user = user;
        this.rate = rate;
    }

    public Rate(BloodBank bloodBank, User user, int rate) {
        this.bloodBank = bloodBank;
        this.user = user;
        this.rate = rate;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", bloodBank=" + bloodBank +
                ", user=" + user +
                ", rate=" + rate +
                '}';
    }
}
