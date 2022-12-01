package com.example.demo.model;

import com.example.demo.model.enumerations.AppointmentStatus;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_sequence"
    )
    private Long id;
    @OneToOne
    @JoinColumn(name = "bb_id",referencedColumnName = "id")
    private BloodBank bloodBank;
    private Date date;
    private LocalTime time;
    private int duration; //in minutes
    @Transient
    private List<User> medicalStuff;
    @Enumerated(EnumType.STRING)
    @Column(name="appstatus")
    private AppointmentStatus status;

    public Appointment() {
    }

    public Appointment(Long id, BloodBank bloodBank, Date date, LocalTime time, int duration, List<User> medicalStuff, AppointmentStatus status) {
        this.id = id;
        this.bloodBank = bloodBank;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.medicalStuff = medicalStuff;
        this.status = status;
    }

    public Appointment(BloodBank bloodBank, Date date, LocalTime time, int duration, List<User> medicalStuff, AppointmentStatus status) {
        this.bloodBank = bloodBank;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.medicalStuff = medicalStuff;
        this.status = status;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<User> getMedicalStuff() {
        return medicalStuff;
    }

    public void setMedicalStuff(List<User> medicalStuff) {
        this.medicalStuff = medicalStuff;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", bloodBank=" + bloodBank +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", medicalStuff=" + medicalStuff +
                ", status=" + status +
                '}';
    }
    
    public boolean isAppointmentInWorkTime() {
    	WorkTime bbWorkTime = this.bloodBank.getWorkTime();
    	LocalTime startTime = this.time;
    	LocalTime endTime = this.time.plusMinutes(this.duration);
    	int compareStartWithStart = startTime.compareTo(bbWorkTime.getStart());
    	int compareStartWithEnd = startTime.compareTo(bbWorkTime.getEnd());
    	int compareEndWithStart = endTime.compareTo(bbWorkTime.getStart());
    	int compareEndWithEnd = endTime.compareTo(bbWorkTime.getEnd());
    	
    	if(compareStartWithStart > 0 && compareStartWithEnd < 0 &&
    			compareEndWithStart > 0 && compareEndWithEnd < 0) 
    		return true;
    	else
    		return false;
    }
    
    public boolean isAppointmentOverlapsWithOtherAppointment(Appointment app) {
    	LocalTime start1 = this.time;
    	LocalTime end1 = this.time.plusMinutes(this.duration);
    	LocalTime start2 = app.getTime();
    	LocalTime end2 = app.getTime().plusMinutes(app.getDuration());
    	
    	int compareEnd1WithStart2 = end1.compareTo(start2);
    	int compareStar1WithEnd2 = start1.compareTo(end2);
    	
    	if(compareEnd1WithStart2 < 0 || compareStar1WithEnd2 > 0) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    public boolean isAppointmentInThePast() {
    	Date currentDate = new Date();
    	LocalTime currentTime = LocalTime.now();
    	
    	int compareCurrentTimeWithTimeOfApp = currentTime.compareTo(this.time);
    	
    	if(currentDate.getDate() > this.date.getDate()) {
    		return true;
    	}else if(compareCurrentTimeWithTimeOfApp > 0) {
    		return true;
    	}else {
    		return false;
    	}
    }
}