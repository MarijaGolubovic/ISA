package com.example.demo.configuration;

import com.example.demo.model.Address;
import com.example.demo.model.BloodBank;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.User;
import com.example.demo.model.WorkTime;
import com.example.demo.model.enumerations.BloodType;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.BloodSupplyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

@Configuration
public class BloodSupplyConfig {
	@Bean
    CommandLineRunner commandLineRunner4(BloodSupplyRepository repository){
        return args ->{/*
        	
        	BloodBank bankaKrvi1 = new BloodBank(1L,
                    "Moja Banka Krvi",
                    "Uvek tu u teskim vremenima",
                    9.3,
                    new Address("Srbija", "Novi Sad", "Bulevar oslobodjenja", "2a"),
                    new HashSet<>(),  new WorkTime(LocalTime.parse("08:00:00"), LocalTime.parse("16:00:00")));
            User user1 = new User("ilija.ilic@gmail.com", "123", "Ilija", "Ilic", null, "064522255", "1236548956324", Gender.MALE, "menadzer", "",  UserType.ADMIN_CENTER, UserStatus.ACTIVATED, 0,0, bankaKrvi1);
            bankaKrvi1.addAdmin(user1);
            
            BloodSupply bs = new BloodSupply(BloodType.ABneg, 1000000.0, bankaKrvi1);
        	
            repository.save(bs);*/
        };
	}

}
