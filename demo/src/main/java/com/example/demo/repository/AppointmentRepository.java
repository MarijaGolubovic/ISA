package com.example.demo.repository;


import java.util.List;

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

	@Query("select app from Appointment app where app.bloodBank.id = ?1 and app.status = \'BUSY\'")
	List<Appointment> getAppointmentsByBloodBankID(Long id);

	@Query("select app from Appointment app where app.user.id = ?1")
	List<Appointment> getByUserId(long l);

	@Query("select app from Appointment app where app.id = ?1")
	Appointment getById(long l);

	@Query("select app from Appointment app where app.bloodBank.id = ?1 and app.status = \'DONE\'")
	List<Appointment> getDoneAppointmentsByBloodBankID(Long id);

	@Query("select app from Appointment app where app.status = \'FREE\'")
	List<Appointment> getFreeAppointments();

	@Query("select app from Appointment app where app.user.id = ?1 and app.status = \'DONE\'")
	List<Appointment> getDoneAppointmentsForUser(Long id);

	@Query("select app from Appointment app where app.user.id = ?1 and app.status = \'BUSY\'")
	List<Appointment> getBusyAppointmentsForUser(Long id);

}
