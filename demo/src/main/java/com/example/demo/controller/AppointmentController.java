package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Appointment;
import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.dto.BloodTypeDTO;
import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.dto.FutureAppointmentDTO;
import com.example.demo.dto.FutureAppointmentsBBDTO;
import com.example.demo.dto.SurveyDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.enumerations.AppointmentStatus;
import com.example.demo.dto.AppointmentUserDTO;
import com.example.demo.dto.AppoitmentScheduleDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.BloodBankService;
import com.example.demo.service.BloodSupplyService;
import com.google.gson.Gson;

import java.io.Console;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="api/appointments")
public class AppointmentController {

	private final AppointmentService appService;
	private final BloodBankService bbService;
	private final BloodSupplyService bsService;
	
	@Autowired
	public AppointmentController(AppointmentService appservice, BloodBankService bbService, BloodSupplyService bsService) {
		this.appService = appservice;
		this.bbService = bbService;
		this.bsService = bsService;
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
	
	//ovdje ide id logovanog usera
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllFutureAppointmentsForLoggedUser")
    public List<FutureAppointmentDTO> getAllFutureAppointmentsForLoggedUser() {
		return this.appService.getAllFutureAppointmentsForLoggedUser(1L);
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAll")
	public List<Appointment> getAll() {
		return this.appService.getAll();
	}

	//ovdje ide id logovanog usera
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllFutureAppointmentResponsesForLoggedUser")
	public List<AppointmentResponse> getAllFutureAppointmentResponsesForLoggedUser() {
		return this.appService.getAllFutureAppointmentResponsesForLoggedUser();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "/schedule", method = RequestMethod.PUT)
	public Appointment scheduleAppointment(@RequestBody CreateAppointmentDTO appDTO) {
		Appointment app = appService.convertCreateAppointmentDTOtoAppointment(appDTO);
		Appointment app1 = this.appService.scheduleAppointment(app);
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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/demonstrateConcurentAccessToDataBase")
	public String demonstrateConcurentAccessToDataBase() throws Throwable {
		this.appService.demonstrateConcurentAccessToDataBase();
		return "";
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAppointment/{iD}")
	public AppointmentUserDTO getAllForAdminCenter(@PathVariable Long iD) {
		Appointment app= appService.getById(iD);		
		return appService.convertAppointmentToAppointmentUserDTO(app);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "/addSurvey/{iD}", method = RequestMethod.PUT)
	public void addSurvey(@RequestBody SurveyDTO surveyDTO,@PathVariable Long iD) {
		Appointment app= appService.getById(iD);
		app.setSurvey(appService.convertSurveyDTOToSurvey(surveyDTO)); 
		app.setStatus(AppointmentStatus.DONE);
		bsService.addDonatedQuantity(app.getBloodBank().getId(), 1, app.getSurvey().getBloodType());
		appService.update(app);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllFutureAppointmentsForBB/{iD}")
    public List<FutureAppointmentsBBDTO> getAllFutureAppointmentsBB(@PathVariable Long iD) {
		return this.appService.getAllFutureAppointmentsBB(2);
    }
}
