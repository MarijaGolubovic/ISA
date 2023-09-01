package com.example.demo.service;

import com.example.demo.dto.PenalsNumberDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.CanceledApointments;
import com.example.demo.model.User;
import com.example.demo.repository.CanceledApointmentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CanceledAppointmentService {
    private final CanceledApointmentRepository canceledApointmentRepository;
    private final UserRepository userRepository;

//    private final AppointmentService appointmentService;

    public CanceledAppointmentService(CanceledApointmentRepository canceledApointmentRepository,
                                      UserRepository userRepository) {
        this.canceledApointmentRepository = canceledApointmentRepository;
        this.userRepository = userRepository;

    }

    public void save(CanceledApointments apointments){
        canceledApointmentRepository.save(apointments);
    }

    public List<CanceledApointments>findByUserId(Long userId){
        return canceledApointmentRepository.findAllByUserId(userId);
    }

}
