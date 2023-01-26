package com.example.demo.controller;

import com.example.demo.dto.BloodSupplyDTO;
import com.example.demo.dto.BloodTypeDTO;
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
    
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/supply/{iD}")
    public List<BloodSupplyDTO> getBloodSupplyByBBID(@PathVariable Long iD){
        return bloodSupplyService.convertToDTO(bloodSupplyService.getBloodSupplyByBBID(iD));
    }

    @GetMapping("/bloodType/{bloodType}")
    @ResponseBody
    public boolean checkBloodType(@PathVariable BloodTypeDTO bloodType){
        return bloodSupplyService.checkBloodType(bloodType);
    }

    @RequestMapping(path = "/bloodType/{bloodType}/{quantity}", method = RequestMethod.GET)
    public boolean checkBloodTypeAndQuantity(@PathVariable BloodTypeDTO bloodType, @PathVariable int quantity) {
        return bloodSupplyService.checkBloodTypeAndQuantity(bloodType,quantity);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/{bloodBankName}/{bloodType}/{quantity}", method = RequestMethod.GET)
    public boolean checkBloodSupplyByBloodBank(@PathVariable String bloodBankName, @PathVariable BloodType bloodType, @PathVariable int quantity) {
        return bloodSupplyService.checkBloodSupplyByBloodBank(bloodBankName,bloodType,quantity);
    }
}
