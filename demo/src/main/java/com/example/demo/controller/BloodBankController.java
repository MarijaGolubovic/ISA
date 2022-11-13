package com.example.demo.controller;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.model.BloodBank;
import com.example.demo.service.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/bloodbank")
public class BloodBankController {

    private final BloodBankService BloodBankService;

    @Autowired
    public BloodBankController(BloodBankService BloodBankService){
        this.BloodBankService = BloodBankService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public boolean register(@RequestBody BloodBankRegistrationRequest bb) {
        return BloodBankService.save(bb);
    }

}
