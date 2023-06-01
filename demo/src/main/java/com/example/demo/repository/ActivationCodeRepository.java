package com.example.demo.repository;

import com.example.demo.model.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationCodeRepository extends JpaRepository<ActivationCode,Long> {
    ActivationCode findByCode(String code);
}
