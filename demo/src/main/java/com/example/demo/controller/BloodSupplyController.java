package com.example.demo.controller;

import com.example.demo.model.BloodSupply;
import com.example.demo.service.BloodSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/blood")
public class BloodSupplyController {

    private final BloodSupplyService bloodSupplyService;

    @Autowired
    public BloodSupplyController(BloodSupplyService bloodSupplyService){
        this.bloodSupplyService = bloodSupplyService;
    }

    @GetMapping
    public List<BloodSupply> getBloodSupply(){
        return bloodSupplyService.getBloodSupply();
    }
}
