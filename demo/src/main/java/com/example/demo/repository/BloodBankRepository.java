package com.example.demo.repository;

import com.example.demo.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank,Long> {

}
