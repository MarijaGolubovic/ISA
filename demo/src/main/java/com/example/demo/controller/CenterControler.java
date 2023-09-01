package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Appointment;
import com.example.demo.model.BloodBank;
import com.example.demo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.CenterResponse;
import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.dto.DateTimeDTO;
import com.example.demo.service.CenterService;
import com.google.gson.Gson;

@RestController
@RequestMapping(path="api/centers")
public class CenterControler {
	
	private final CenterService CenterService;
	public CenterControler(CenterService CenterService) {
		this.CenterService=CenterService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
	@GetMapping
    public List<CenterResponse> getAllCenterResponses(){
        return CenterService.getAllCentersResponses();
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
	@RequestMapping(path = "/loggedAdminCenter/{id}", method = RequestMethod.GET)
	public BloodBank getLoggedUserCenter (@PathVariable Long id) {
			return CenterService.getLoggedUserCenter(id);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_ADMIN_SISTEM')")
	@RequestMapping(path = "/loggedAdminCenter/saveCenter", method = RequestMethod.PUT)
	public void saveCenter(@RequestBody BloodBank bb) {
		this.CenterService.SaveCenter(bb);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
    @PostMapping("/getAllAvailableCenters")
    public List<CenterResponse> getAllAvailableCenters(@RequestBody DateTimeDTO dateTimeDTO) {
		return this.CenterService.getAllAvailableCenters(dateTimeDTO);
    }

}
