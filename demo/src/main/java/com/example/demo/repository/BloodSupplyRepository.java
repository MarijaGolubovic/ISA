package com.example.demo.repository;

import com.example.demo.model.BloodBank;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodSupplyRepository extends JpaRepository<BloodSupply,Long> {

    //@Query("select u from User u where u.emailAddress = ?1")
    @Query("select bs from BloodSupply bs where bs.bloodType = ?1")
    BloodSupply findByBloodType(BloodType bloodType);

    @Query("select bs from BloodSupply bs where bs.bloodBank.id = (select bb from BloodBank bb where bb.id=?2) and bs.bloodType = ?1")
    BloodSupply findBloodSupplyByBloodBank(BloodType bloodType, Long id);

}
