package com.example.demo.dto;

import java.time.LocalTime;
import java.util.Date;

import com.example.demo.model.Questionnaire;
import com.example.demo.model.enumerations.AppointmentStatus;

public class AppointmentUserDTO {
	private Long id;
    private Date date;
    private LocalTime time;
    private int duration; //in minutes
    private AppointmentStatus status;
    private Long userID;
    private QuestionnairuDTO questionnaire;
    
    
	public QuestionnairuDTO getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(QuestionnairuDTO questionnaire) {
		this.questionnaire = questionnaire;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public AppointmentStatus getStatus() {
		return status;
	}
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public AppointmentUserDTO(Long id, Date date, LocalTime time, int duration, AppointmentStatus status, Long userID,
			QuestionnairuDTO questionnaire) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.status = status;
		this.userID = userID;
		this.questionnaire = questionnaire;
	}

    
}
