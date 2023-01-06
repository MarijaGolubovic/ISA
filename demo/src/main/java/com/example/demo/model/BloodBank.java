package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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
    private String email;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL)
    private Set<User> administrators = new HashSet<User>();
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "worktime_id",referencedColumnName = "id")
    private WorkTime workTime;
    @JsonIgnore
    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL)
    private List<BloodSupply> bloodSupplies;
    private String apiKey;

    public BloodBank() {
    }

    
    

    public List<BloodSupply> getBloodSupplies() {
		return bloodSupplies;
	}




	public void setBloodSupplies(List<BloodSupply> bloodSupplies) {
		this.bloodSupplies = bloodSupplies;
	}




	public BloodBank(String name, String description, double averageRate, Address address, Set<User> administrators,
			WorkTime workTime,String apiKey, String email) {
		super();
		this.name = name;
		this.description = description;
		this.averageRate = averageRate;
		this.address = address;
		this.administrators = administrators;
		this.workTime = workTime;
		this.apiKey = apiKey;
        this.email=email;
	}




	public BloodBank(Long id, String name, String description, double averageRate, Address address,
			Set<User> administrators, WorkTime workTime, String apiKey) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageRate = averageRate;
		this.address = address;
		this.administrators = administrators;
		this.workTime = workTime;
		this.apiKey = apiKey;
	}

    public BloodBank(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
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
