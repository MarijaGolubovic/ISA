package com.example.demo.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Address;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.BloodType;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.BloodSupplyRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class TestDataConfiguration {
	/*@Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, BloodSupplyRepository bloodSupplyRepository){
        return args ->{
        	
        	BloodBank bloodBank1 = new BloodBank
        	
        	BloodSupply bloodSupply1 = new BloodSupply(
        			BloodType.ABneg,
        			100,
        			
        			);
            User Dejan = new User(
                    "nemanja.nemanjic@gmail.com",
                    "123",
                    "Dejan",
                    "Gloginjic",
                    new Address("Srbija", "Novi Sad", "Juraja Krizanica", "11A"),
                    "065/026-549",
                    "1234567891235",
                    Gender.MALE,
                    "Programer",
                    "Informacije 1",
                    UserType.ADMIN_SISTEM,
                    UserStatus.ACTIVATED,
                    0,
                    0,
					null,
					null
            );
            User Nikolina = new User(
                    "nikolina.nikolic@gmail.com",
                    "123",
                    "Nikolina",
                    "Nikolic",
                    new Address("Srbija", "Novi Sad", "Juraja Krizanica", "12A"),
                    "065/026-549",
                    "1234567891235",
                    Gender.FEMALE,
                    "Menadzer",
                    "",
                    UserType.ADMIN_CENTER,
                    UserStatus.ACTIVATED,
                    0,
                    0,
					null,
					null
            );
            userRepository.save(Dejan);
            userRepository.save(Nikolina);
        };
	}*/
}
