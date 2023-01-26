package com.example.demo.service;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.dto.BloodSupplyDTO;
import com.example.demo.dto.BloodTypeDTO;
import com.example.demo.model.BloodBank;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.enumerations.BloodType;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.BloodSupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    
    public List<BloodSupply> getBloodSupplyByBBID(long id){
        return BloodSupplyRepository.getBloodSupplyByBBID(id);
    }
    
    public List<BloodSupplyDTO> convertToDTO (List<BloodSupply> bloods) {
    	List<BloodSupplyDTO>  dto = new ArrayList<BloodSupplyDTO>();
    	
    	for (BloodSupply bs:bloods) {
    		dto.add(new BloodSupplyDTO(bs.getId(), bs.getBloodType(), bs.getQuantity(), bs.getBloodBank().getId()));
    	}
    	
    	return dto;    	
    }
    
    public BloodTypeDTO convertEnum(BloodType bt) {
    	
    	if (bt.equals(BloodType.Apos))
    		return BloodTypeDTO.Apos;
    	else if (bt.equals(BloodType.Aneg))
    		return BloodTypeDTO.Aneg;
    	else if (bt.equals(BloodType.Bpos))
    		return BloodTypeDTO.Bpos;
    	else if (bt.equals(BloodType.Bneg))
    		return BloodTypeDTO.Bneg;
    	else if (bt.equals(BloodType.ABpos))
    		return BloodTypeDTO.ABpos;
    	else if (bt.equals(BloodType.ABneg))
    		return BloodTypeDTO.ABneg;
    	else if (bt.equals(BloodType.Opos))
    		return BloodTypeDTO.Opos;
    	else
    		return BloodTypeDTO.Oneg;   	
    }

    public boolean checkBloodType(BloodTypeDTO bloodType){
        BloodSupply bs =  BloodSupplyRepository.findByBloodType(bloodType);
        if (bs == null){
            return false;
        }
        if (bs.getQuantity() > 0)
            return true;
        else
            return false;
    }

    public boolean checkBloodTypeAndQuantity(BloodTypeDTO bloodType,int quantity){
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

    public void reduceForDeliveredQuantity(String bloodBankName, int quantity, BloodTypeDTO bloodType){
        BloodSupply bloodSupply = BloodSupplyRepository.getByBloodBankNameAndBloodType(bloodBankName,bloodType);
        double currentQuantity = bloodSupply.getQuantity();
        bloodSupply.setQuantity(currentQuantity-quantity);
        this.BloodSupplyRepository.save(bloodSupply);
    }
    
    public void addDonatedQuantity(Long bloodBankiD, double quantity, BloodType bloodType){
        BloodSupply bloodSupply = this.BloodSupplyRepository.getByBloodBankIdAndBloodTypeS(bloodBankiD,bloodType);
        double currentQuantity = bloodSupply.getQuantity();
        bloodSupply.setQuantity(currentQuantity+quantity);
        this.BloodSupplyRepository.save(bloodSupply);
    }
    
    

}
