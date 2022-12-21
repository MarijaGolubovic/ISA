package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.model.Appointment;
import com.example.demo.dto.AppoitmentScheduleDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.BloodBankService;
import com.google.gson.Gson;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllForAdminCenter/{adminEmail}")
	public List<AppoitmentScheduleDto> getAllForAdminCenter(@PathVariable String adminEmail) {
		List<Appointment> list= appService.findByAdminCenter(adminEmail);
		List<AppoitmentScheduleDto> list1 = new ArrayList<>();

		for (Appointment a:list) {
			Date EndDate = new Date(a.getDate().getTime() + 3600000);
			AppoitmentScheduleDto asd = new AppoitmentScheduleDto("PREGLED", a.getDate(), EndDate);
			list1.add(asd);
		}
		return list1;
	}

}
