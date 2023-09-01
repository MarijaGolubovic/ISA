package com.example.demo.repository;

import com.example.demo.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode,Long> {
    @Query("SELECT qr FROM QRCode qr WHERE qr.appointmentId = ?1")
    QRCode findByAppointmentId(Long appointmentId);

    @Query("SELECT qr FROM QRCode qr WHERE qr.userId = ?1")
    List<QRCode> findByUserId(Long userId);


}
