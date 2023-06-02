package com.example.demo.repository;

import com.example.demo.model.CanceledApointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CanceledApointmentRepository extends JpaRepository<CanceledApointments, Long> {

    @Query("SELECT ca FROM CanceledApointments ca WHERE ca.userId = ?1")
    List<CanceledApointments> findAllByUserId(Long userId);

}
