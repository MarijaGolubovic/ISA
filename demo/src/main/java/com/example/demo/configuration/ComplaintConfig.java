package com.example.demo.configuration;

import com.example.demo.model.*;
import com.example.demo.model.enumerations.ComplaintStatus;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.BloodSupplyRepository;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Configuration
public class ComplaintConfig {
    /*@Bean
    CommandLineRunner commandLineRunner5(ComplaintRepository repository, ComplaintService service){
        return args ->{

        	BloodBank bankaKrvi1 = new BloodBank(
                    "Moja Banka Krvi",
                    "Uvek tu u teskim vremenima",
                    9.3,
                    new Address("Srbija", "Novi Sad", "Bulevar oslobodjenja", "2a"),
                    new HashSet<>(),  new WorkTime(LocalTime.parse("08:00:00"), LocalTime.parse("16:00:00")), "x");
            User user1 = new User("ilija.ilic@gmail.com", "123", "Ilija", "Ilic", null, "064522255", "1236548956324", Gender.MALE, "menadzer", "",  UserType.ADMIN_CENTER, UserStatus.ACTIVATED, 0,0, bankaKrvi1);
            bankaKrvi1.addAdmin(user1);

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

            Complaint complaint = new Complaint(bankaKrvi1, Nikolina, "U bankakrvi1 je losa higijena.", new Date(), ComplaintStatus.PENDING, "");
            Complaint complaint1 = new Complaint(bankaKrvi1, Nikolina, "U bankakrvi1 nije ljubazno osoblje.", new Date(), ComplaintStatus.PENDING, "");
            Complaint complaint2 = new Complaint(bankaKrvi1, Nikolina, "balblala.", new Date(), ComplaintStatus.PENDING, "");

            //service.save(complaint);
            //service.save(complaint1);
            //service.save(complaint2);
        };
    }*/
}
