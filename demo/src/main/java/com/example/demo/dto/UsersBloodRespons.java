package com.example.demo.dto;

import java.time.LocalTime;
import java.util.Date;

public class UsersBloodRespons {
	private String name;
    private String surname;
    private String email;
    private Date date;
    private LocalTime time;

    public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public UsersBloodRespons() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UsersBloodRespons(String name, String surname, String email, Date date, LocalTime time) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.date = date;
		this.time = time;
	}




}
