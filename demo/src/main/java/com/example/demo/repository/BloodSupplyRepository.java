package com.example.demo.repository;

import com.example.demo.dto.BloodType;
import com.example.demo.model.BloodBank;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.enumerations.BloodType2;

import java.util.List;

import javax.persistence.Column;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BloodSupplyRepository extends JpaRepository<BloodSupply,Long> {

    //@Query("select u from User u where u.emailAddress = ?1")
    @Query("select bs from BloodSupply bs where bs.bloodType = ?1")
    BloodSupply findByBloodType(com.example.demo.model.enumerations.BloodType bloodType);
    
    //bs.bb_id = 5 and bs.bloodtype= \'Apos\'
    @Query("SELECT bs FROM BloodSupply bs WHERE bs.bloodBank.id = ?1 AND bs.bloodType = ?2")
    BloodSupply getByBloodBankIdAndBloodType(Long bloodBankId, BloodType bloodTipe);

    @Query("select bs from BloodSupply bs where bs.bloodBank.id = (select bb from BloodBank bb where bb.id=?2) and bs.bloodType = ?1")
    BloodSupply findBloodSupplyByBloodBank(com.example.demo.model.enumerations.BloodType bloodType, Long id);

}
