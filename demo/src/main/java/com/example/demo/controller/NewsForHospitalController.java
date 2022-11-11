package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.NewsForHospital;
import com.example.demo.publisher.RabbitMQProducer;
import com.google.gson.Gson;

@RestController
@RequestMapping(path="api/news")
public class NewsForHospitalController {

	private final RabbitMQProducer producer;
	
	@Autowired
	public NewsForHospitalController(RabbitMQProducer producer) {
		this.producer = producer;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
    public void sendNews(@RequestBody String news) {
		NewsForHospital n = new NewsForHospital("Naslov", "Kontent", "Vampir");
		String newsForHospital = new Gson().toJson(n);
    	producer.sendMessage(newsForHospital);
    }
}
