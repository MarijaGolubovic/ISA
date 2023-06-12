package com.example.demo.service;
import com.example.demo.repository.BloodSupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.model.BloodBank;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.AddressRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.Optional;

@Service
public class BloodBankService {
    private final BloodBankRepository BloodBankRepository;
    private final AddressRepository AddressRepository;
    private final BloodSupplyRepository bloodSupplyRepository;

    @Autowired
    public BloodBankService(BloodBankRepository BloodBankRepository, AddressRepository AddressRepository,
                            BloodSupplyRepository bloodSupplyRepository){
        this.BloodBankRepository = BloodBankRepository;
        this.AddressRepository = AddressRepository;
        this.bloodSupplyRepository = bloodSupplyRepository;
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

    @Transactional
    public boolean changeBloodUnits(Long bloodBankId, Integer bloodUnitsFromRequest) {
        Optional<BloodBank> optionalBloodBank = BloodBankRepository.findById(bloodBankId);

        if (optionalBloodBank.isPresent()) {
            BloodBank bloodBank = optionalBloodBank.get();
            int newBloodUnits = bloodBank.getBags() - bloodUnitsFromRequest;

            bloodBank.setBags(newBloodUnits);
            bloodBank.setVersion(bloodBank.getVersion() + 1);

            try {
                return changeBloodBank(bloodBank);
            } catch (OptimisticLockException ex) {
                return false;
            }
        }
        return false;
    }



    public BloodBank getById(Long bloodBankId){
        return BloodBankRepository.findByID(bloodBankId);
    }

    public boolean changeBloodBank(BloodBank bloodBank) {
        Optional<BloodBank> storedBloodBank = BloodBankRepository.findById(bloodBank.getId());

        if (storedBloodBank.isPresent()) {
            BloodBank stored = storedBloodBank.get();
            if (stored.getVersion().equals(bloodBank.getVersion())) {
                BloodBankRepository.save(bloodBank);
                return true; // Ažuriranje uspješno
            } else {
                return false; // Optimističko zaključavanje nije uspjelo
            }
        } else {
            throw new EntityNotFoundException("BloodBank with id " + bloodBank.getId() + " not found.");
        }
    }


}
