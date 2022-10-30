package com.example.demo.service;

import com.example.demo.model.BloodSupply;
import com.example.demo.repository.BloodSupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodSupplyService {

    private final BloodSupplyRepository BloodSupplyRepository;

    @Autowired
    public BloodSupplyService(BloodSupplyRepository BloodSupplyRepository){
        this.BloodSupplyRepository = BloodSupplyRepository;
    }

    public List<BloodSupply> getBloodSupply(){
        return BloodSupplyRepository.findAll();
    }

    public boolean checkBloodType(String bloodType){
        BloodSupply bs =  BloodSupplyRepository.findByBloodType(bloodType);
        if (bs.getQuantity() > 0)
            return true;
        else
            return false;
    }

    public boolean checkBloodTypeAndQuantity(String bloodType,int quantity){
        BloodSupply bs =  BloodSupplyRepository.findByBloodType(bloodType);
        if (bs.getQuantity() > quantity)
            return true;
        else
            return false;
    }

}
