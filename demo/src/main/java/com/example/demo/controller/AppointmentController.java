package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.*;
import com.example.demo.service.UserService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Appointment;
import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.dto.FutureAppointmentDTO;
import com.example.demo.dto.FutureAppointmentsBBDTO;
import com.example.demo.dto.SurveyDTO;
import com.example.demo.model.enumerations.AppointmentStatus;
import com.example.demo.dto.AppointmentUserDTO;
import com.example.demo.dto.AppoitmentScheduleDto;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.BloodBankService;
import com.example.demo.service.BloodSupplyService;
import com.google.gson.Gson;

import javax.mail.MessagingException;
import java.util.Date;

@RestController
@RequestMapping(path="api/appointments")
public class AppointmentController {

	private final AppointmentService appService;
	private final BloodBankService bbService;
	private final BloodSupplyService bsService;
	private final UserService userService;
	
	@Autowired
	public AppointmentController(AppointmentService appservice, BloodBankService bbService, BloodSupplyService bsService, UserService userService) {
		this.appService = appservice;
		this.bbService = bbService;
		this.bsService = bsService;
		this.userService = userService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED')")
    @PostMapping("/isAvailable")
    public String getMessageAboutAvailability(@RequestBody CreateAppointmentDTO appDTO) {
		Appointment app = appService.convertCreateAppointmentDTOtoAppointment(appDTO);
		String message = new Gson().toJson(appService.getMessageAboutAvailability(app));
		System.out.println("////////////////////////////"+message);
		return message;
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED')")
	@RequestMapping(path = "/create", method = RequestMethod.PUT)
	public Appointment saveAppointment(@RequestBody CreateAppointmentDTO appDTO) {
		Appointment app = appService.convertCreateAppointmentDTOtoAppointment(appDTO);
		Appointment app1 = this.appService.saveAppointment(app);
		System.out.println("Hello========================== " + app1.getId());
		return app1;
	}



	//ovdje ide id logovanog usera
	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
    @GetMapping("/getAllFutureAppointmentsForLoggedUser")
    public List<FutureAppointmentDTO> getAllFutureAppointmentsForLoggedUser() {
		return this.appService.getAllFutureAppointmentsForLoggedUser(userService.getCurrentUser().getId());
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED')")
	@GetMapping("/getAll")
	public List<Appointment> getAll() {
		return this.appService.getAll();
	}

	//ovdje ide id logovanog usera
	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED')")
	@GetMapping("/getAllFutureAppointmentResponsesForLoggedUser")
	public List<AppointmentResponse> getAllFutureAppointmentResponsesForLoggedUser() {
		return this.appService.getAllFutureAppointmentResponsesForLoggedUser();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED')")
	@RequestMapping(path = "/schedule", method = RequestMethod.PUT)
	public Appointment scheduleAppointment(@RequestBody CreateAppointmentDTO appDTO) {
		Appointment app = appService.convertCreateAppointmentDTOtoAppointment(appDTO);
		Appointment app1 = this.appService.scheduleAppointment(app);
		return app1;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllForAdminCenter/{adminEmail}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER')")
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

//	@CrossOrigin(origins = "http://localhost:4200")
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED')")
//	@GetMapping("/getAppointment/{iD}")
//	public AppointmentUserDTO getAllForAdminCenter(@PathVariable Long iD) {
//		Appointment app= appService.getById(iD);
//		return appService.convertAppointmentToAppointmentUserDTO(app);
//	}

//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(path = "/addSurvey/{iD}", method = RequestMethod.PUT)
//	public void addSurvey(@RequestBody SurveyDTO surveyDTO,@PathVariable Long iD) {
//		Appointment app= appService.getById(iD);
//		app.setSurvey(appService.convertSurveyDTOToSurvey(surveyDTO));
//		app.setStatus(AppointmentStatus.DONE);
//		bsService.addDonatedQuantity(app.getBloodBank().getId(), 1, app.getSurvey().getBloodType());
//		appService.update(app);
//	}

	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllFutureAppointmentsForBB/{iD}")
    public List<FutureAppointmentsBBDTO> getAllFutureAppointmentsBB(@PathVariable Long iD) {
		return this.appService.getAllFutureAppointmentsBB(2);
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED')")
	@GetMapping("/getAllFree")
	public List<Appointment> getAllFree() {
		System.out.print("=============================="+appService.getAll().size()+"\n");
		return appService.getFreeAppointments();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_REGISTERED')")
	@PostMapping("/takeAppointment/{appointmentId}")
	public int takeAppointment(@PathVariable Long appointmentId ) throws IOException, WriterException, MessagingException {
		return appService.takeAppointment(userService.getCurrentUser().getId(), appointmentId);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED')")
	@GetMapping("/getHistoryForUser")
	public List<Appointment> getHistoryForUser() {

		return appService.getHistoryForUser(userService.getCurrentUser().getId());
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
	@GetMapping("/getBusyApointmentForUser")
	public List<Appointment> getBusyApointmentForUser() {
		return this.appService.getBusyAppointments(userService.getCurrentUser().getId());
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_REGISTERED')")
	@PostMapping("/cancelAppointment/{appointmentId}")
	public boolean cancelAppointment(@PathVariable Long appointmentId ) {
		return appService.cancelApointment(appointmentId);
	}

}
