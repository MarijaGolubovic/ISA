package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.CenterRepository;
import com.example.demo.dto.CenterResponse;
import com.example.demo.model.*;



@Service
public class CenterService {
	private final CenterRepository CenterRepository;
	
	
@Autowired
public CenterService(CenterRepository centerRepository) {
	this.CenterRepository=centerRepository;
}

public List<BloodBank> getAllUsers(){
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
    centerResponse.setName(center.getName());
    centerResponse.setCity(center.getAddress().getCity());
    centerResponse.setStreet(center.getAddress().getStreet());
    centerResponse.setGrade(center.getAverageRate());

    return centerResponse;
}

}
