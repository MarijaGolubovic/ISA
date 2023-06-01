package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Address;
import com.example.demo.model.BloodBank;
import com.example.demo.model.NewsForHospital;
import com.example.demo.model.User;
import com.example.demo.model.WorkTime;
import com.example.demo.publisher.RabbitMQProducer;
import com.example.demo.service.BloodBankService;
import com.google.gson.Gson;

@RestController
@RequestMapping(path="api/news")
public class NewsForHospitalController {

	private final RabbitMQProducer producer;
	private final BloodBankService bloodBankService;
	
	@Autowired
	public NewsForHospitalController(RabbitMQProducer producer, BloodBankService bbService) {
		this.producer = producer;
		this.bloodBankService = bbService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
	@PostMapping
    public void sendNews(@RequestBody NewsForHospital news) {
		news.setApiKey("NkwQR/sa7Rm97+S7/KQxqWl2nZhnWjzLX3dvHOTngEk=");
		news.setBloodBankName("David Albala");
		String newsForHospital = new Gson().toJson(news);
    	producer.sendMessage(newsForHospital);
    }
}
