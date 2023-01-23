package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Address;
import com.example.demo.model.BloodBank;
import com.example.demo.model.MounthlyBloodSubscription;

@Repository
public interface MounthlyBloodSubscriptionRepository extends JpaRepository<MounthlyBloodSubscription,Long>{

	@Query("select b from MounthlyBloodSubscription b where b.bloodBank.id=?1")
    List<MounthlyBloodSubscription> findByBloodBankID(Long bloodBankId);
}
