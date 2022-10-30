package com.example.demo.controller;

import com.example.demo.model.BloodSupply;
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

    /*@GetMapping
    public List<BloodSupply> getBloodSupply(){
        return bloodSupplyService.getBloodSupply();
    }*/

    @GetMapping("/bloodType/{bloodType}")
    @ResponseBody
    public boolean checkBloodType(@PathVariable String bloodType){
        return bloodSupplyService.checkBloodType(bloodType);
    }

    /*@RequestMapping(value = "/BloodType/{bloodType}/Quantity/{quantity}", method = RequestMethod.GET)
    public boolean checkBloodTypeAndQuantity(@PathVariable(value = "BloodType") String bloodType, @PathVariable(value = "Quantity") int quantity) {
        return bloodSupplyService.checkBloodTypeAndQuantity(bloodType,quantity);
    }*/

    @GetMapping
    public boolean getBloodSupply(){
        return true;
    }
}
