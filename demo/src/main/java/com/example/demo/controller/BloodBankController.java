package com.example.demo.controller;

import com.example.demo.dto.BloodBankRegistrationRequest;
import com.example.demo.model.BloodBank;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.service.BloodBankService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/bloodbank")
public class BloodBankController {

    private final BloodBankService BloodBankService;
    private final UserService UserService;

    @Autowired
    public BloodBankController(BloodBankService BloodBankService, UserService UserService){
        this.BloodBankService = BloodBankService;
        this.UserService = UserService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<BloodBank> getAll() { return BloodBankService.getAll();}

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_SISTEM')")
    @PostMapping("/register")
    public boolean register(@RequestBody BloodBankRegistrationRequest bb) {
        return BloodBankService.save(bb);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_SISTEM')")
    @PostMapping("/add-admin-centar/{bankName}")
    public void AddAdminCenter(@RequestBody User user,@PathVariable String bankName) {
        BloodBank bb = BloodBankService.getByName(bankName);
        bb.getAdministrators().add(user);
        user.setUserType(UserType.ADMIN_CENTER);
        user.setBloodBank(bb);
        System.out.println(user);
        UserService.saveUser(user);
    }

}
