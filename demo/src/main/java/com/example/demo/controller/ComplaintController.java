package com.example.demo.controller;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.Complaint;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.BloodBankService;
import com.example.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Complaint> getAll(){
        return complaintService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pending")
    public List<Complaint> getAllOnPending(){
        return complaintService.getAllOnPending();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/update")
    public void register(@RequestBody Complaint c) {
        complaintService.update(c);
    }

}
