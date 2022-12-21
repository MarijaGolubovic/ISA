package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Address;
import com.example.demo.model.Appointment;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
    @Query("select a from Appointment a where a.bloodBank.id = (select u.bloodBank.id from User u where u.email=?1)")
    List<Appointment> findByAdminCenter(String email);

}
