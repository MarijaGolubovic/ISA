package com.example.demo.repository;

import com.example.demo.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRCodeRepository extends JpaRepository<QRCode,Long> {
}
