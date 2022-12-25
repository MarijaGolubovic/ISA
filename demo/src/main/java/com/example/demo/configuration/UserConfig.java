package com.example.demo.configuration;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.BloodSupplyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
	/*@Bean
	    CommandLineRunner commandLineRunner(UserRepository repository){
	        return args ->{
	            User Dejan = new User(
	                    "nemanja.nemanjic@gmail.com",
	                    "123",
	                    "Dejan",
	                    "Gloginjic",
	                    null,
	                    "065/026-549",
	                    "1234567891235",
	                    Gender.MALE,
	                    "nastavnik",
	                    "",
	                    UserType.ADMIN_SISTEM,
	                    UserStatus.ACTIVATED,
	                    0,
	                    0,
						null
	            );
	            User Nikolina = new User(
	                    "nikolina.nikolic@gmail.com",
	                    "123",
	                    "Nikolina",
	                    "Nikolic",
	                    null,
	                    "065/026-549",
	                    "1234567891235",
	                    Gender.FEMALE,
	                    "menadzer",
	                    "",
	                    UserType.ADMIN_CENTER,
	                    UserStatus.ACTIVATED,
	                    0,
	                    0,
						null
	            );
				//repository.save(Dejan);
				//repository.save(Nikolina);
	        };
	    }*/
	}