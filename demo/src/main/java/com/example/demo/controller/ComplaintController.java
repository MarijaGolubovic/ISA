package com.example.demo.controller;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.Complaint;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.BloodBankService;
import com.example.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<Complaint> register(@RequestBody Complaint complaint) throws Exception{
        Complaint updatedComplaint = null;
        try{
            updatedComplaint = complaintService.update(complaint);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<Complaint>(HttpStatus.I_AM_A_TEAPOT); // :)
        }
        return new ResponseEntity<Complaint>(updatedComplaint, HttpStatus.OK);
    }

}
