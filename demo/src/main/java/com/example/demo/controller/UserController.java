package com.example.demo.controller;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<UserResponse> getAllUserResponses(){
        return userService.getAllUserResponses();
    }


}