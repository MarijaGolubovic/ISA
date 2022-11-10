package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.CenterRepository;

import antlr.collections.List;


@Service
public class CenterService {
	private final CenterRepository CenterRepository;
	
	
@Autowired
public CenterService(CenterRepository centerRepository) {
	this.CenterRepository=centerRepository;
}


}
