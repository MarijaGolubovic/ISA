package com.example.demo;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AmountOfBloodTypeResponse;
import com.example.demo.dto.BloodType;
import com.example.demo.dto.MounthlyBloodSubscriptionRequest;
import com.example.demo.model.AmountOfBloodType;
import com.example.demo.model.BloodBank;
import com.example.demo.model.MounthlyBloodSubscription;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.MounthlyBloodSubscriptionRepository;
import com.google.gson.Gson;

import org.springframework.context.annotation.Bean;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);
  private final MounthlyBloodSubscriptionRepository bloodSubRepo;
  private final BloodBankRepository bbRepo;
  
  public Receiver(MounthlyBloodSubscriptionRepository bloodSubRepo, BloodBankRepository bbRepo) {
	  this.bloodSubRepo = bloodSubRepo;
	  this.bbRepo = bbRepo;
  }

  public void receiveMessage(byte[] message) {
	  String s = new String(message, StandardCharsets.UTF_8);
	  System.out.println("Received <" + s + ">");
	  Gson gson = new Gson(); // Or use new GsonBuilder().create();
	  MounthlyBloodSubscriptionRequest bloodSubReq = gson.fromJson(s, MounthlyBloodSubscriptionRequest.class);
	  
	  if(bloodSubReq.getDateAndTimeOfSubscription() == null) {
		  return;
	  }
	  
	  BloodBank bb = bbRepo.findByAPIKey(bloodSubReq.getAPIKey());
	  List<AmountOfBloodType> aobtList = new ArrayList();
	  
	  for(AmountOfBloodTypeResponse a : bloodSubReq.getBloodTypeAmountPair()) {
		  AmountOfBloodType aobt = new AmountOfBloodType(a.getBloodType(), a.getAmount());
		  aobtList.add(aobt);
	  }
	  
	  MounthlyBloodSubscription bloodSub = new MounthlyBloodSubscription(bb, bloodSubReq.getAPIKey(), bloodSubReq.getDateAndTimeOfSubscription(), aobtList);
	  
	  for(AmountOfBloodType a : bloodSub.getAmountOfBloodTypes()) {
		  a.setBloodSub(bloodSub);
	  }
	  
	  bloodSub.setAmountOfBloodTypes(aobtList);
	  
	  this.bloodSubRepo.save(bloodSub);
	  latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}
