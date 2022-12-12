package com.example.demo.controller;

import com.example.demo.model.BloodSupply;
import com.example.demo.model.enumerations.BloodType;
import com.example.demo.service.BloodSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/bloodType/{bloodType}")
    @ResponseBody
    public boolean checkBloodType(@PathVariable BloodType bloodType){
        return bloodSupplyService.checkBloodType(bloodType);
    }

    @RequestMapping(path = "/bloodType/{bloodType}/{quantity}", method = RequestMethod.GET)
    public boolean checkBloodTypeAndQuantity(@PathVariable BloodType bloodType, @PathVariable int quantity) {
        return bloodSupplyService.checkBloodTypeAndQuantity(bloodType,quantity);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/{bloodBankName}/{bloodType}/{quantity}", method = RequestMethod.GET)
    public boolean checkBloodSupplyByBloodBank(@PathVariable String bloodBankName, @PathVariable BloodType bloodType, @PathVariable int quantity) {
        return bloodSupplyService.checkBloodSupplyByBloodBank(bloodBankName,bloodType,quantity);
    }
}
