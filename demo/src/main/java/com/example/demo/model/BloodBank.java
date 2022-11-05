package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class BloodBank {

    @Id
    @SequenceGenerator(
            name = "bloodbank_sequence",
            sequenceName = "bloodbank_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bloodbank_sequence"
    )
    private Long id;
    private String name;
    private String description;
    private double averageRate;
    private Adress adress;
    private List<User> administrators;
    private WorkTime workTime;

    public BloodBank() {
    }

    public BloodBank(Long id, String name, String description, double averageRate, Adress adress, List<User> administrators, WorkTime workTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.averageRate = averageRate;
        this.adress = adress;
        this.administrators = administrators;
        this.workTime = workTime;
    }

    public BloodBank(String name, String description, double averageRate, Adress adress, List<User> administrators, WorkTime workTime) {
        this.name = name;
        this.description = description;
        this.averageRate = averageRate;
        this.adress = adress;
        this.administrators = administrators;
        this.workTime = workTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public List<User> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<User> administrators) {
        this.administrators = administrators;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    @Override
    public String toString() {
        return "BloodBank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", averageRate=" + averageRate +
                ", adress=" + adress +
                ", administrators=" + administrators +
                ", workTime=" + workTime +
                '}';
    }
}
