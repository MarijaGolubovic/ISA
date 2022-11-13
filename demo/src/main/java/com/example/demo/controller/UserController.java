package com.example.demo.controller;

import com.example.demo.dto.EditedUserResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.Address;
import com.example.demo.model.BloodSupply;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.modelmapper.ModelMapper;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/search/{name}/{surname}", method = RequestMethod.GET)
    public List<UserResponse> getAllUsersResponseForNameAndSurname(@PathVariable String name, @PathVariable String surname){
        return userService.getAllUsersResponseForNameAndSurname(name,surname);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/getLoggedUser", method = RequestMethod.GET)
    public User getLoggedUser() {
    	User user = userService.getAllUsers().get(10);
    	return user;
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/saveUser", method = RequestMethod.PUT)
    public void saveUser(@RequestBody User u) {
    	this.userService.saveUser(u);
    }

    @GetMapping("/{bloodBankName}")
    @ResponseBody
    public List<UserResponse> getAllUsersForAdminCenter(@PathVariable String bloodBankName){
        return userService.getAllUsersForAdminCenter(bloodBankName);
    }

}