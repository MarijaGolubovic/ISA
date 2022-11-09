package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;
    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL)
    private Set<User> administrators = new HashSet<User>();
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "worktime_id",referencedColumnName = "id")
    private WorkTime workTime;

    public BloodBank() {
    }

    public BloodBank(Long id, String name, String description, double averageRate, Address address, Set<User> administrators, WorkTime workTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.averageRate = averageRate;
        this.address = address;
        this.administrators = administrators;
        this.workTime = workTime;
    }

    public BloodBank(String name, String description, double averageRate, Address address, Set<User> administrators, WorkTime workTime) {
        this.name = name;
        this.description = description;
        this.averageRate = averageRate;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address adress) {
        this.address = adress;
    }

    public Set<User> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(Set<User> administrators) {
        this.administrators = administrators;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public void addAdmin(User user) {
        administrators.add(user);
        user.setBloodBank(this);
    }

    public void removeAdmin(User user) {
        administrators.remove(user);
        user.setBloodBank(null);
    }
    @Override
    public String toString() {
        return "BloodBank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", averageRate=" + averageRate +
                ", address=" + address +
                ", administrators=" + administrators +
                ", workTime=" + workTime +
                '}';
    }
}
