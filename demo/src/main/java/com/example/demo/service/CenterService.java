package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.CenterRepository;
import com.example.demo.dto.CenterResponse;
import com.example.demo.dto.DateTimeDTO;
import com.example.demo.model.*;



@Service
public class CenterService {
	
	private final CenterRepository CenterRepository;
	private final AppointmentRepository appointmentRepository;
	
	@Autowired
	public CenterService(CenterRepository centerRepository, AppointmentRepository appRepo) {
		this.CenterRepository=centerRepository;
		this.appointmentRepository = appRepo;
	}


	public List<BloodBank> getAllBloodBanks(){
	    return CenterRepository.findAll();
	}

	public List<CenterResponse> getAllCentersResponses(){
	    return CenterRepository.findAll()
	            .stream()
	            .map(this::convertEntityToDto)
	            .collect(Collectors.toList());
	}

	private CenterResponse convertEntityToDto(BloodBank center){
	    CenterResponse centerResponse  = new CenterResponse();
	    centerResponse.setId(center.getId());
	    centerResponse.setName(center.getName());
	    centerResponse.setCity(center.getAddress().getCity());
	    centerResponse.setStreet(center.getAddress().getStreet());
	    centerResponse.setStreetNumber(center.getAddress().getNumber());
	    centerResponse.setGrade(center.getAverageRate());
	
	    return centerResponse;
	}

    public BloodBank getLoggedUserCenter (Long UserId) {
        return CenterRepository.findByUserId(UserId);
    }

    public void SaveCenter (BloodBank bb) {
        this.CenterRepository.save(bb);
    }


	public List<CenterResponse> getAllAvailableCenters(DateTimeDTO dateTimeDTO) {
		List<CenterResponse> retList = new ArrayList<CenterResponse>();
		List<BloodBank> allBanks = this.getAllBloodBanks();
		
		for(BloodBank bb : allBanks) {
			
			if(!bb.isDateTimeInWorkTime(dateTimeDTO)) 
				break;
			
			List<Appointment> appointments = this.appointmentRepository.getAppointmentsByBloodBankID(bb.getId());
			boolean flag = false;
			
			for(Appointment app : appointments) {
				if(app.isAppointmentOverlapsWithDateTime(dateTimeDTO)) {
					flag = true;
				}
			}
			if(flag)
				break;
			
			CenterResponse cr = convertEntityToDto(bb);
			
			retList.add(cr);
		}
		
		return retList;
	}


}
