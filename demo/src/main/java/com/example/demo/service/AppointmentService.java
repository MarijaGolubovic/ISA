package com.example.demo.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.BloodBank;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.AppointmentStatus;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.CenterRepository;

@Service
public class AppointmentService {
	private final AppointmentRepository appRepo;
	private final BloodBankRepository bloodRepo;
	private final CenterRepository bbRepo;
	
	@Autowired
	public AppointmentService(AppointmentRepository repo, CenterRepository bbRepo, BloodBankRepository bloodRepo) {
		this.appRepo = repo;
		this.bbRepo = bbRepo;
		this.bloodRepo = bloodRepo;
	}
	
	public String getMessageAboutAvailability(Appointment app) {
		List<Appointment> appointments = appRepo.findAll();
		
		if(app.isAppointmentInThePast()) {
			return("Can not schedule appointment in the past!");
		}
		
		if(!app.isAppointmentInWorkTime()) {
			return("Time of appointment is out of blood bank's work time!");
		}
		
		for(Appointment appointment : appointments) {
			if(app.getDate().getDate() == appointment.getDate().getDate()) {
				if(app.isAppointmentOverlapsWithOtherAppointment(appointment)) {
					return("Appointment overlaps with other appointments!");
				}
			}
		}
		
		return("Available");
	}
	
	public Appointment saveAppointment(Appointment app) {
		return appRepo.save(app);
	}
	
	@SuppressWarnings("deprecation")
	public Appointment convertCreateAppointmentDTOtoAppointment(CreateAppointmentDTO appDTO) {
		BloodBank bb = bbRepo.getOne(appDTO.getBloodBankID());
        LocalTime time = LocalTime.parse(appDTO.getTime());
        Appointment app = new Appointment(bb, appDTO.getDate(), time, appDTO.getDuration(), null, appDTO.getStatus());
        
        return app;
	}

	public void save(Appointment appointment){
		bloodRepo.save(appointment.getBloodBank());
		appRepo.save(appointment);
	}

	public List<Appointment> findByAdminCenter(String email){
		return appRepo.findByAdminCenter(email);
	}

}
