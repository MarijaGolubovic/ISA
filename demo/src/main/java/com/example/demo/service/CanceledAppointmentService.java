package com.example.demo.service;

import com.example.demo.model.CanceledApointments;
import com.example.demo.repository.CanceledApointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class CanceledAppointmentService {
    private final CanceledApointmentRepository canceledApointmentRepository;

    public CanceledAppointmentService(CanceledApointmentRepository canceledApointmentRepository) {
        this.canceledApointmentRepository = canceledApointmentRepository;
    }

    public void save(CanceledApointments apointments){
        canceledApointmentRepository.save(apointments);
    }
}
