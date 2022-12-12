package com.example.demo.service;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.model.BloodBank;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.enumerations.BloodType;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.BloodSupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodSupplyService {

    private final BloodSupplyRepository BloodSupplyRepository;
    private final com.example.demo.repository.AddressRepository AddressRepository;
    private final BloodBankRepository BloodBankRepository;

    @Autowired
    public BloodSupplyService(BloodSupplyRepository BloodSupplyRepository,BloodBankRepository BloodBankRepository, AddressRepository AddressRepository){
        this.BloodSupplyRepository = BloodSupplyRepository;
        this.BloodBankRepository = BloodBankRepository;
        this.AddressRepository = AddressRepository;
    }

    public List<BloodSupply> getBloodSupply(){
        return BloodSupplyRepository.findAll();
    }

    public boolean checkBloodType(BloodType bloodType){
        BloodSupply bs =  BloodSupplyRepository.findByBloodType(bloodType);
        if (bs == null){
            return false;
        }
        if (bs.getQuantity() > 0)
            return true;
        else
            return false;
    }

    public boolean checkBloodTypeAndQuantity(BloodType bloodType,int quantity){
        BloodSupply bs =  BloodSupplyRepository.findByBloodType(bloodType);
        if (bs == null) {
            return false;
        }else{
            if (bs.getQuantity() >= quantity)
                return true;
            else
                return false;
        }
    }

    public boolean checkBloodSupplyByBloodBank(String bloodBankName, BloodType bloodType, int quantity){
        BloodBank bb = BloodBankRepository.findByName(bloodBankName);
        if (bb == null) {
            return false;
        }
        BloodSupply bs =  BloodSupplyRepository.findBloodSupplyByBloodBank(bloodType, bb.getId());
        if (bs == null) {
            return false;
        }else{
            if (bs.getQuantity() >= quantity)
                return true;
            else
                return false;
        }
    }

    public void save(BloodSupply bloodSupply){
        this.BloodBankRepository.save(bloodSupply.getBloodBank());
        this.AddressRepository.save(bloodSupply.getBloodBank().getAddress());
        this.BloodSupplyRepository.save(bloodSupply);
    }

}
