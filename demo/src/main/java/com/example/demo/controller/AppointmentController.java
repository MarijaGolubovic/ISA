package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.BloodBank;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.BloodBankService;
import com.google.gson.Gson;

@RestController
@RequestMapping(path="api/appointments")
public class AppointmentController {

	private final AppointmentService appService;
	private final BloodBankService bbService;
	
	@Autowired
	public AppointmentController(AppointmentService appservice, BloodBankService bbService) {
		this.appService = appservice;
		this.bbService = bbService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/isAvailable")
    public String getMessageAboutAvailability(@RequestBody CreateAppointmentDTO appDTO) {
		Appointment app = appService.convertCreateAppointmentDTOtoAppointment(appDTO);
		String message = new Gson().toJson(appService.getMessageAboutAvailability(app));
        return message;
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "/create", method = RequestMethod.PUT)
	public Appointment saveAppointment(@RequestBody CreateAppointmentDTO appDTO) {
		Appointment app = appService.convertCreateAppointmentDTOtoAppointment(appDTO);
		Appointment app1 = this.appService.saveAppointment(app);
		return app1;
	}
}
