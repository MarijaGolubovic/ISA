package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.CenterResponse;
import com.example.demo.service.CenterService;

@RestController
@RequestMapping(path="api/centers")
public class CenterControler {
	
	private final CenterService CenterService;
	public CenterControler(CenterService CenterService) {
		this.CenterService=CenterService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
    public List<CenterResponse> getAllCenterResponses(){
        return CenterService.getAllCentersResponses();
    }
}
