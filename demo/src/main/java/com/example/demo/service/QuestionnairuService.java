package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Questionnaire;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionnairuRepository;

@Service
public class QuestionnairuService {
	private final QuestionnairuRepository QuestionnairuRepository;
	
	@Autowired
	public QuestionnairuService(QuestionnairuRepository questionnairuRepository) {
		this.QuestionnairuRepository=questionnairuRepository;
	}
	
	public void saveQuestionaire(Questionnaire question)
	{
		question.setTimeOfExecution(LocalDate.now());
        this.QuestionnairuRepository.save(question);
    }
}
