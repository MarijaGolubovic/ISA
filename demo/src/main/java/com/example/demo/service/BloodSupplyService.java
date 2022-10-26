package com.example.demo.service;

import com.example.demo.model.BloodSupply;
import com.example.demo.repository.BloodSupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodSupplyService {

    private final BloodSupplyRepository bloodSupplyRepository;

    @Autowired
    public BloodSupplyService(BloodSupplyRepository bloodSupplyRepository){
        this.bloodSupplyRepository = bloodSupplyRepository;
    }

    public List<BloodSupply> getBloodSupply(){
        return bloodSupplyRepository.findAll();
    }
}
