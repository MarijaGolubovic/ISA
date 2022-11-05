package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Adress {

    @Id
    @SequenceGenerator(
            name = "adress_sequence",
            sequenceName = "adress_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "adress_sequence"
    )
    private Long id;
    private String country;
    private String city;
    private String street;
    private String number;

    public Adress() { }

    public Adress( String country, String city, String street, String number) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Adress(Long id, String country, String city, String street, String number) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
