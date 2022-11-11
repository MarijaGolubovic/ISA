package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BloodBank;

public interface CenterRepository extends JpaRepository<BloodBank, Long> {

}
