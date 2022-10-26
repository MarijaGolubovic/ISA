package com.example.demo.repository;

import com.example.demo.model.BloodSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodSupplyRepository extends JpaRepository<BloodSupply,Long> {

}
