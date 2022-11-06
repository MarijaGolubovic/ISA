package com.example.demo.repository;

import com.example.demo.model.BloodBank;
import com.example.demo.model.BloodSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodSupplyRepository extends JpaRepository<BloodSupply,Long> {

    //@Query("select u from User u where u.emailAddress = ?1")
    @Query("select bs from BloodSupply bs where bs.bloodType = ?1")
    BloodSupply findByBloodType(String bloodType);

}
