package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.BloodBank;
import com.example.demo.model.Questionnaire;
import com.example.demo.model.User;
import com.example.demo.service.QuestionnairuService;
import com.google.gson.Gson;

@RestController
@RequestMapping(path="api/questionnaire")
public class QuestionnairuController {

	private final QuestionnairuService QuestionnairuService;
	
	@Autowired
	public QuestionnairuController(QuestionnairuService questionnairuService) {
		this.QuestionnairuService=questionnairuService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping
    public void saveQuestionnairu(@RequestBody Questionnaire question) {
    	this.QuestionnairuService.saveQuestionaire(question);
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/checkIfQuestionnaireHasBeenCompletedInLastSixMonths")
    public boolean checkIfQuestionnaireHasBeenCompletedInLastSixMonths(@RequestBody long userId) {
		return this.QuestionnairuService.checkIfQuestionnaireHasBeenCompletedInLastSixMonths(userId);
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path = "/getQuestionnairForUser/{id}", method = RequestMethod.GET)
	public Questionnaire getQuestionnairForUser (@PathVariable Long id) {
			return QuestionnairuService.getLastQuestionnaire(id);
	}
}
