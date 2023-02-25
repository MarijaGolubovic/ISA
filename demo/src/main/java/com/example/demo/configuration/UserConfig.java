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
				repository.save(Dejan);
				repository.save(Nikolina);
	        };
	    }*/
	}