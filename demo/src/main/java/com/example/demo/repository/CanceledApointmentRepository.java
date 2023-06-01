package com.example.demo.repository;

import com.example.demo.model.CanceledApointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanceledApointmentRepository extends JpaRepository<CanceledApointments, Long> {
}
