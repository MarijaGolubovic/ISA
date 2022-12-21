package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Address;
import com.example.demo.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

	@Query("select app from Appointment app where app.bloodBank.id = ?1 and app.status = \'BUSY\'")
	List<Appointment> getAppointmentsByBloodBankID(Long id);

}
