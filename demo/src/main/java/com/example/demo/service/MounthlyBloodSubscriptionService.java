package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AmountOfBloodTypeResponse;
import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.dto.MounthlyBloodSubscriptionResponse;
import com.example.demo.model.AmountOfBloodType;
import com.example.demo.model.Appointment;
import com.example.demo.model.BloodBank;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.MounthlyBloodSubscription;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.AppointmentStatus;
import com.example.demo.publisher.RabbitMQBloodSubscriptionProducer;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.BloodSupplyRepository;
import com.example.demo.repository.CenterRepository;
import com.example.demo.repository.MounthlyBloodSubscriptionRepository;
import com.google.gson.Gson;

@Service
public class MounthlyBloodSubscriptionService {
	private final MounthlyBloodSubscriptionRepository bloodSubRepo;
	private final BloodSupplyRepository bloodSupplyRepo;
	private final RabbitMQBloodSubscriptionProducer producer;
	
	@Autowired
	public MounthlyBloodSubscriptionService(MounthlyBloodSubscriptionRepository bloodSubRepo, BloodSupplyRepository bloodSuppltRepo, 
			RabbitMQBloodSubscriptionProducer producerr) {
		this.bloodSubRepo = bloodSubRepo;
		this.bloodSupplyRepo = bloodSuppltRepo;
		this.producer = producerr;
	}
	
	public List<MounthlyBloodSubscription> findAll(){
		return this.bloodSubRepo.findAll();
	}
	
	@Scheduled(fixedRate = 1000)
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public void scheduleTaskWithFixedRate() {
		List<MounthlyBloodSubscription> allBloodSub = findAll();
		
		for(MounthlyBloodSubscription bs : allBloodSub) {
			
			//if(LocalDateTime.now() == bs.getDateAndTimeOfSubscription().plusMinutes(5))
			if(true) {
				
				List<AmountOfBloodTypeResponse> currentStateOfAmounts = getCurrentState(bs);
				
				String messageForManager = generateMessageForManager(currentStateOfAmounts, bs.getAmountOfBloodTypes());
				
				MounthlyBloodSubscriptionResponse response = new MounthlyBloodSubscriptionResponse(messageForManager, bs.getAPIKey(), currentStateOfAmounts);
				
				String responseForHospital = new Gson().toJson(response);
				
				producer.sendJsonMessage(responseForHospital);
			}
			
				
			
		}
	}
	
	private List<AmountOfBloodTypeResponse> getCurrentState(MounthlyBloodSubscription bs){
		
		List<AmountOfBloodTypeResponse> currentStateOfAmounts = new ArrayList<>();
		
		for(AmountOfBloodType aobt : bs.getAmountOfBloodTypes()) {
			BloodSupply bloodSupply = this.bloodSupplyRepo.getByBloodBankIdAndBloodType(bs.getBloodBank().getId(), aobt.getBloodType());
			if(bloodSupply == null) {
				
				AmountOfBloodTypeResponse currentAmount = new AmountOfBloodTypeResponse(aobt.getBloodType(), 0);
				currentStateOfAmounts.add(currentAmount);
				
			}else if(bloodSupply.getQuantity() < aobt.getAmount()) {
				
				AmountOfBloodTypeResponse currentAmount = new AmountOfBloodTypeResponse(aobt.getBloodType(), bloodSupply.getQuantity().intValue());
				currentStateOfAmounts.add(currentAmount);
				
			}else if(bloodSupply.getQuantity() > aobt.getAmount()) {
				
				bloodSupply.setQuantity(bloodSupply.getQuantity() - aobt.getAmount());
				this.bloodSupplyRepo.save(bloodSupply);
				AmountOfBloodTypeResponse currentAmount = new AmountOfBloodTypeResponse(aobt.getBloodType(), aobt.getAmount());
				currentStateOfAmounts.add(currentAmount);
				
			}
		}
		
		return currentStateOfAmounts;
	}
	
	private String generateMessageForManager(List<AmountOfBloodTypeResponse> currentStateOfAmounts, List<AmountOfBloodType> set) {
		String message = "Dear," + "\r\n";
		
		return message;
	}
}
