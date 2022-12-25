package com.example.demo.configuration;

import com.example.demo.model.Address;
import com.example.demo.model.BloodBank;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.User;
import com.example.demo.model.WorkTime;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.BloodBankRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
public class BloodBankConfig {
   //@Bean
    /*CommandLineRunner commandLineRunner1(BloodBankRepository repository){
        return args ->{


            BloodBank bankaKrvi1 = new BloodBank(
                    "Moja Banka Krvi",
                    "Uvek tu u teskim vremenima",
                    9.3,
                    new Address("Srbija", "Novi Sad", "Bulevar oslobodjenja", "2a"),
                    new HashSet<>(),  new WorkTime(LocalTime.parse("08:00:00"), LocalTime.parse("16:00:00")), "x");
            User user1 = new User("ilija.ilic@gmail.com", "123", "Ilija", "Ilic", null, "064522255", "1236548956324", Gender.MALE, "menadzer", "",  UserType.ADMIN_CENTER, UserStatus.ACTIVATED, 0,0, bankaKrvi1);
            bankaKrvi1.addAdmin(user1);
            repository.saveAll(List.of(bankaKrvi1));
            
            BloodBank bankaKrvi2 = new BloodBank(
                    "Nova Banka Krvi",
                    "Cuvajmo ono sto je vrijedno",
                    7.3,
                    new Address("Srbija", "Novi Sad", "Bulevar cara Lazara", "12b"),
                    new HashSet<>(),  new WorkTime(LocalTime.parse("08:00:00"), LocalTime.parse("16:00:00")), "x");
            User user2 = new User("nikola.nikolic@gmail.com", "123", "Nikola", "Nikolic", null, "064522321", "1236548906324", Gender.MALE, "menadzer", "",  UserType.ADMIN_CENTER, UserStatus.ACTIVATED, 0,0, bankaKrvi2);
            bankaKrvi2.addAdmin(user2);
            repository.saveAll(List.of(bankaKrvi2));

        	 BloodBank bankaKrvi3 = new BloodBank(
                     "Narodna banka krvi",
                     "Tecnost koja znaci zivot",
                     8.9,
                     new Address("Srbija", "Beograd", "Narodnog fronta", "15a"),
                     new HashSet<>(),  new WorkTime(LocalTime.parse("08:00:00"), LocalTime.parse("16:00:00")), "x");
             User user3 = new User("ana.nedic@gmail.com", "124", "Ana", "Nedic", null, "064522321", "1246548906324", Gender.FEMALE, "menadzer", "",  UserType.ADMIN_CENTER, UserStatus.ACTIVATED, 0,0, bankaKrvi3);

             User user4=new User("ivana.ivic@gmail.com", "124", "Ivana", "Ivic", null, "064522321", "1246548906324", Gender.FEMALE, "menadzer", "",  UserType.ADMIN_CENTER, UserStatus.ACTIVATED, 0,0, bankaKrvi3);
             bankaKrvi3.addAdmin(user3);
            bankaKrvi3.addAdmin(user4);
             repository.saveAll(List.of(bankaKrvi3));
            

             bankaKrvi3.addAdmin(user3);
             repository.save(bankaKrvi3);
        	
        };
    }*/


}
