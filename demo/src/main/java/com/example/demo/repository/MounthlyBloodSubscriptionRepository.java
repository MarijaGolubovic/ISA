package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Address;
import com.example.demo.model.MounthlyBloodSubscription;

@Repository
public interface MounthlyBloodSubscriptionRepository extends JpaRepository<MounthlyBloodSubscription,Long>{

}
