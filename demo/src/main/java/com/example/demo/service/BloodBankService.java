package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.model.BloodBank;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BloodBankService {
    private final BloodBankRepository BloodBankRepository;
    private final AddressRepository AddressRepository;

    @Autowired
    public BloodBankService(BloodBankRepository BloodBankRepository, AddressRepository AddressRepository){
        this.BloodBankRepository = BloodBankRepository;
        this.AddressRepository = AddressRepository;
    }

    public boolean save(BloodBankRegistrationRequest bb){
        BloodBank bloodBank = convertDtoToEntity(bb);
        if (BloodBankRepository.save(bloodBank) != null){
            return true;
        }else return false;
    }

    public BloodBank getByName(String name){return BloodBankRepository.findByName(name);}

    public List<BloodBank> getAll(){ return BloodBankRepository.findAll();}

    public BloodBank convertDtoToEntity(BloodBankRegistrationRequest bb){
        AddressRepository.save(bb.getAddress());
        BloodBank bloodBank = new BloodBank();
        bloodBank.setName(bb.getName());
        bloodBank.setAddress(bb.getAddress());
        bloodBank.setAverageRate(0.0);
        bloodBank.setWorkTime(null);
        bloodBank.setDescription(bb.getDescription());
        return bloodBank;
    }

    @SuppressWarnings("deprecation")
	public BloodBank GetById(Long id) {
		return this.BloodBankRepository.getOne(id);
	}

    
}
