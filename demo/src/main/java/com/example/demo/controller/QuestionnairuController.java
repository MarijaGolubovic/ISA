package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Questionnaire;
import com.example.demo.model.User;
import com.example.demo.service.QuestionnairuService;

@RestController
@RequestMapping(path="api/questionnaire")
public class QuestionnairuController {

	private final QuestionnairuService QuestionnairuService;
	
	@Autowired
	public QuestionnairuController(QuestionnairuService questionnairuService) {
		this.QuestionnairuService=questionnairuService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
//    @RequestMapping(path = "/save", method = RequestMethod.PUT)
	@PutMapping
    public void saveQuestionnairu(@RequestBody Questionnaire question) {
    	this.QuestionnairuService.saveQuestionaire(question);
    }
}
