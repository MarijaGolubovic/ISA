package com.example.demo.service;

import com.example.demo.model.Complaint;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.ComplaintStatus;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository ComplaintRepository;
    private final BloodBankRepository BloodBankRepository;
    private final UserRepository UserRepository;

    @Autowired
    public ComplaintService(ComplaintRepository ComplaintRepository, BloodBankRepository BloodBankRepository, UserRepository UserRepository){
        this.ComplaintRepository = ComplaintRepository;
        this.BloodBankRepository = BloodBankRepository;
        this.UserRepository = UserRepository;
    }

    public List<Complaint> getAll() {return ComplaintRepository.findAll();}

    public List<Complaint> getAllOnPending() {
        //return ComplaintRepository.findAllOnPending();
        List<Complaint> list = ComplaintRepository.findAll();
        List<Complaint> listPending = new ArrayList<>();
        for (Complaint complaint: list) {
            if (complaint.getStatus() == ComplaintStatus.PENDING){
                listPending.add(complaint);
            }
        }
        return listPending;
    }

    public void save(Complaint complaint){
        this.BloodBankRepository.save(complaint.getBloodBank());
        this.UserRepository.save(complaint.getUser());
        this.ComplaintRepository.save(complaint);
    }

    public void update(Complaint complaint){
        ComplaintRepository.delete(complaint);
        ComplaintRepository.save(complaint);
    }

}
