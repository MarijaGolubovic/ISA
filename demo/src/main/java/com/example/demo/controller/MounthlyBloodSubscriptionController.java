package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AmountOfBloodTypeDTO;
import com.example.demo.dto.BloodSubscriptionDTO;
import com.example.demo.dto.FutureAppointmentDTO;
import com.example.demo.model.AmountOfBloodType;
import com.example.demo.model.MounthlyBloodSubscription;
import com.example.demo.service.MounthlyBloodSubscriptionService;

@RestController
@RequestMapping(path="api/bloodSubscription")
public class MounthlyBloodSubscriptionController {

	private final MounthlyBloodSubscriptionService bloodSubService;

	@Autowired
	public MounthlyBloodSubscriptionController(MounthlyBloodSubscriptionService bloodSubService) {
		this.bloodSubService = bloodSubService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
    @GetMapping
    public List<BloodSubscriptionDTO> getAllFutureAppointmentsForLoggedUser(Long id) {
		List<BloodSubscriptionDTO> retList = new ArrayList<BloodSubscriptionDTO>();
		for(MounthlyBloodSubscription m : this.bloodSubService.findAllByBloodBankID(id)) {
			retList.add(convertToDTO(m));
		}
		return retList;
    }
	
	private BloodSubscriptionDTO convertToDTO(MounthlyBloodSubscription bs) {
		BloodSubscriptionDTO dto = new BloodSubscriptionDTO();
		List<AmountOfBloodTypeDTO> aobtList = new ArrayList<AmountOfBloodTypeDTO>();
		dto.setBloodBankName(bs.getBloodBank().getName());
		for(AmountOfBloodType aobt: bs.getAmountOfBloodTypes()) {
			AmountOfBloodTypeDTO a = new AmountOfBloodTypeDTO();
			a.setAmount(aobt.getAmount());
			a.setBloodType(aobt.getBloodType());
			aobtList.add(a);
		}
		dto.setAmountOfBloodTypes(aobtList);
		dto.setDateAndTimeOfSubscription(bs.getDateAndTimeOfSubscription());
		
		return dto;
	}
}
