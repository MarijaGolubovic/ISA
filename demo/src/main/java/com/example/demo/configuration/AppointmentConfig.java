package com.example.demo.configuration;

import com.example.demo.model.*;
import com.example.demo.model.enumerations.AppointmentStatus;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Configuration
public class AppointmentConfig {
    @Bean
    CommandLineRunner commandLineRunner10(AppointmentRepository repository, AppointmentService service){
        return args ->{

            BloodBank bankaKrvi1 = new BloodBank(
                    "Moja Banka Krvi",
                    "Uvek tu u teskim vremenima",
                    9.3,
                    new Address("Srbija", "Novi Sad", "Bulevar oslobodjenja", "2a"),
                    new HashSet<>(),  new WorkTime(LocalTime.parse("08:00:00"), LocalTime.parse("16:00:00")), "x");
            User user1 = new User("ilija.ilic@gmail.com", "123", "Ilija", "Ilic", null, "064522255", "1236548956324", Gender.MALE, "menadzer", "",  UserType.ADMIN_CENTER, UserStatus.ACTIVATED, 0,0, bankaKrvi1);
            bankaKrvi1.addAdmin(user1);

            Appointment ap = new Appointment(
                    bankaKrvi1,
                    new Date(2022,12,25),
                    LocalTime.of(10, 0, 0, 0),
                    30,
                    null,
                    AppointmentStatus.FREE
            );

            Appointment ap1 = new Appointment(
                    bankaKrvi1,
                    new Date(2022,12,30),
                    LocalTime.of(10, 0, 0, 0),
                    30,
                    null,
                    AppointmentStatus.FREE
            );

            Appointment ap2 = new Appointment(
                    bankaKrvi1,
                    new Date(2022,12,31),
                    LocalTime.of(10, 0, 0, 0),
                    30,
                    null,
                    AppointmentStatus.FREE
            );

            //service.save(ap);
            //service.save(ap1);
            //service.save(ap2);
        };
    }


}
