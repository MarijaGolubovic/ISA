package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BloodBank;
import com.example.demo.model.Questionnaire;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.QuestionnairuRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Service
public class QuestionnairuService {
	private final QuestionnairuRepository QuestionnairuRepository;
	private final UserRepository userRepository;
	private final BloodBankRepository bbRepo;
	
	@Autowired
	public QuestionnairuService(QuestionnairuRepository questionnairuRepository, UserRepository userRepo, BloodBankRepository bbRepo) {
		this.QuestionnairuRepository=questionnairuRepository;
		this.userRepository = userRepo;
		this.bbRepo = bbRepo;
	}
	
	//BITNO umjesto usera stavi ulogovanog usera
	//promjeni u klasi Questionnaire da ti user bude notnull
	public void saveQuestionaire(Questionnaire question)
	{
		question.setTimeOfExecution(LocalDate.now());
		//BloodBank bankaKrvi1 = new BloodBank();
        //User user1 = new User("ilija.ilic@gmail.com", "123", "Ilija", "Ilic", null, "064522255", "1236548956324", Gender.MALE, "menadzer", "",  UserType.ADMIN_CENTER, UserStatus.ACTIVATED, 0,0, bankaKrvi1, List.of(question));
		question.setUser(userRepository.getOne(1L));
		//this.userRepository.save(user1);
        this.QuestionnairuRepository.save(question);
    }

	public boolean checkIfQuestionnaireHasBeenCompletedInLastSixMonths(long userId) {
		List<Questionnaire> all = this.QuestionnairuRepository.findAllByUserId(userId);
		LocalDate nowMinusSixMounths = LocalDate.now().minusMonths(6);
		if(all.size() == 0) {
			return false;
		}
		Questionnaire mostRecentQuestionnaire = all.get(0);
		for(Questionnaire q : all) {
			if(mostRecentQuestionnaire.getTimeOfExecution().compareTo(q.getTimeOfExecution()) < 0) {
				mostRecentQuestionnaire = q;
			}
		}
		
		if(nowMinusSixMounths.compareTo(mostRecentQuestionnaire.getTimeOfExecution()) > 0) {
			return true;
		}
		return true;
	}
}
