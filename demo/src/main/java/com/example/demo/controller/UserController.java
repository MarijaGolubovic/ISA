package com.example.demo.controller;

import com.example.demo.dto.EditedUserResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UsersBloodRespons;
import com.example.demo.model.Address;
import com.example.demo.model.BloodBank;
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
    @GetMapping("/admin-center")
    public List<UserResponse> getAllAdminCentersWithoutBB(){
        return userService.getAdminCentersWithoutBB();
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/getLoggedUser", method = RequestMethod.GET)
    public User getLoggedUser() {
    	User user = userService.getByEmail("nikolina.nikolic@gmail.com");
    	return user;
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/saveUser", method = RequestMethod.PUT)
    public void saveUser(@RequestBody User u) {
        this.userService.saveUser(u);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/admin")
    public List<UserResponse> getAllUsersForAdminCenter(){
        User admin = getLoggedUser();
        return userService.getAllUsersForAdminCenter(admin);
    }
    
    @GetMapping("/{bloodBankName}")
    @ResponseBody
    public List<UserResponse> getAllUsersForAdminCenter(@PathVariable String bloodBankName){
        User user = getLoggedUser();
        return userService.getAllUsersForAdminCenter(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/centersAdmins/{id}", method = RequestMethod.GET)
    public List<User> getCentersAdmins(@PathVariable Long id) {
        return userService.getCentersAdmins(id);
    }

    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/registerUser", method = RequestMethod.PUT)
    public void registerUser(@RequestBody User u) {
    	this.userService.registerUser(u);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/updateBloodBankID/{bloodbankId}", method = RequestMethod.PUT)
    public void update(@RequestBody UserResponse u, @PathVariable String bloodbankId) {
        this.userService.update(u, bloodbankId);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/addStrikes/{id}", method = RequestMethod.PUT)
    public void addStrikes(@PathVariable Long id) {
    	User user = this.userService.getById(id);
    	int str = user.getStrikesNum();
    	str++;
    	user.setStrikesNum(str);
        this.userService.updateUser(user);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/usersBlood/{id}", method = RequestMethod.GET)
    public List<UsersBloodRespons> getDoneAppointmentsUser(@PathVariable Long id) {
        return userService.getDoneAppointmentsUserByBloodBankID(id);
    }
}