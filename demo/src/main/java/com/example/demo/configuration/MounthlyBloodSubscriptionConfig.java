package com.example.demo.configuration;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Address;
import com.example.demo.model.AmountOfBloodType;
import com.example.demo.model.BloodBank;
import com.example.demo.model.MounthlyBloodSubscription;
import com.example.demo.model.User;
import com.example.demo.model.WorkTime;
import com.example.demo.model.enumerations.BloodType2;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.MounthlyBloodSubscriptionRepository;

@Configuration
public class MounthlyBloodSubscriptionConfig {
	/*@Bean
    CommandLineRunner commandLineRunnerBloodSup(MounthlyBloodSubscriptionRepository repository){
        return args ->{

        	BloodBank bankaKrvi1 = new BloodBank(
                    "Moja Banka Krvi",
                    "Uvek tu u teskim vremenima",
                    9.3,
                    new Address("Srbija", "Novi Sad", "Bulevar oslobodjenja", "2a"),
                    new HashSet<>(),  new WorkTime(LocalTime.parse("08:00:00"), LocalTime.parse("16:00:00")), "x");
            User user1 = new User("ilija.ilic@gmail.com", "123", "Ilija", "Ilic", null, "064522255", "1236548956324", Gender.MALE, "menadzer", "",  UserType.ADMIN_CENTER, UserStatus.ACTIVATED, 0,0, bankaKrvi1);
            bankaKrvi1.addAdmin(user1);
        	
            List<AmountOfBloodType> amountOfBloodTypes = new ArrayList<AmountOfBloodType>();
            amountOfBloodTypes.add(new AmountOfBloodType(BloodType2.ABneg, 10));
            amountOfBloodTypes.add(new AmountOfBloodType(BloodType2.Apos, 15));
            
        	MounthlyBloodSubscription bloodSup = new MounthlyBloodSubscription(bankaKrvi1, "x",
        			LocalDateTime.now(), amountOfBloodTypes);
            
            repository.saveAll(List.of(bloodSup));
            
        	
        };
	}*/
}
