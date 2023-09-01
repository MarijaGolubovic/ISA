package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.CanceledApointmentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledTasksService {

    private final CanceledApointmentRepository canceledAppointmentRepository;
    private final UserRepository userRepository;

    public ScheduledTasksService(CanceledApointmentRepository canceledAppointmentRepository, UserRepository userRepository) {
        this.canceledAppointmentRepository = canceledAppointmentRepository;
        this.userRepository = userRepository;
    }

@Scheduled(cron = "0 0 0 1 * ?")
//@Scheduled(cron = "0 */2 * * * *")
public void resetPenalsAndClearCanceledAppointments() {

        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setPenalsNumber(0);
            userRepository.save(user);
        }


        canceledAppointmentRepository.deleteAll();
    System.out.println("****************************************************************");
    System.out.println("****** Penalties have been deleted for the previous month ******");
    System.out.println("****************************************************************");
    }
}

