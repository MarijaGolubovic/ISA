package com.example.demo.repository;

import com.example.demo.model.BloodBank;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.enumerations.BloodType2;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodSupplyRepository extends JpaRepository<BloodSupply,Long> {

    //@Query("select u from User u where u.emailAddress = ?1")
    @Query("select bs from BloodSupply bs where bs.bloodType = ?1")
    BloodSupply findByBloodType(String bloodType);
    
    //bs.bb_id = 5 and bs.bloodtype= \'Apos\'
    @Query("SELECT bs FROM BloodSupply bs WHERE bs.bloodBank.id = ?1 AND bs.bloodType = ?2")
    BloodSupply getByBloodBankIdAndBloodType(Long bloodBankId, BloodType2 bloodTipe);

}
