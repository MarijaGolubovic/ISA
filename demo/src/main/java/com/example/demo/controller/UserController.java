package com.example.demo.controller;

import com.example.demo.dto.PenalsNumberDTO;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ActivationCodeService;
import com.example.demo.service.CanceledAppointmentService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/user")
public class UserController {
//    @PreAuthorize("hasAuthority('ROLE_ADMIN_CENTER')") // Prilagodite ovaj izraz prema svojim potrebama
    private final UserService userService;
    private final ActivationCodeService activationCodeService;
    private final UserRepository userRepository;
    private final CanceledAppointmentService canceledAppointmentService;

    @Autowired
    public UserController(UserService userService, ActivationCodeService activationCodeService,
                          UserRepository userRepository, CanceledAppointmentService canceledAppointmentService){
        this.userService = userService;
        this.activationCodeService = activationCodeService;
        this.userRepository = userRepository;
        this.canceledAppointmentService = canceledAppointmentService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<UserResponse> getAllUserResponses(){
        return userService.getAllUserResponses();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_CENTER')")
    @GetMapping("/admin-center")
    public List<UserResponse> getAllAdminCentersWithoutBB(){
        return userService.getAdminCentersWithoutBB();
    }
    
//    @CrossOrigin(origins = "http://localhost:4200")
//    @RequestMapping(path = "/getLoggedUser", method = RequestMethod.GET)
//    public User getLoggedUser() {
//    	User user = userService.getByEmail("nikolina.nikolic@gmail.com");
//    	return user;
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
    @RequestMapping(path = "/getLoggedUser", method = RequestMethod.GET)
    public User getLoggedUser() {
        User user = userService.getCurrentUser();
        return user;
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
    @RequestMapping(path = "/saveUser", method = RequestMethod.PUT)
    public void saveUser(@RequestBody User u) {
        this.userService.saveUser(u);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_CENTER')")
    @GetMapping("/admin")
    public List<UserResponse> getAllUsersForAdminCenter(){
        User admin = getLoggedUser();
        return userService.getAllUsersForAdminCenter(admin);
    }
    
    @GetMapping("/{bloodBankName}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_REGISTERED', 'ROLE_ADMIN_SISTEM')")
    @ResponseBody
    public List<UserResponse> getAllUsersForAdminCenter(@PathVariable String bloodBankName){
        User user = getLoggedUser();
        return userService.getAllUsersForAdminCenter(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_ADMIN_SISTEM')")
    @RequestMapping(path = "/centersAdmins/{id}", method = RequestMethod.GET)
    public List<User> getCentersAdmins(@PathVariable Long id) {
        return userService.getCentersAdmins(id);
    }

    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/registerUser", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User u) {
    	User user = userRepository.findByEmail(u.getEmail());
        if(user == null){
            this.userService.registerUser(u);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Successfully registered. Please activate your account.");
            return ResponseEntity.ok(response);
        }
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Email is already taken.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_ADMIN_SISTEM')")
    @RequestMapping(path = "/updateBloodBankID/{bloodbankId}", method = RequestMethod.PUT)
    public void update(@RequestBody UserResponse u, @PathVariable String bloodbankId) {
        this.userService.update(u, bloodbankId);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_CENTER')")
    @RequestMapping(path = "/addStrikes/{id}", method = RequestMethod.PUT)
    public void addStrikes(@PathVariable Long id) {
    	User user = this.userService.getById(id);
    	int str = user.getStrikesNum();
    	str++;
    	user.setStrikesNum(str);
        this.userService.updateUser(user);
    }
    
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PreAuthorize("hasAuthority('ROLE_REGISTERED')")
//    @RequestMapping(path = "/usersBlood/{id}", method = RequestMethod.GET)
//    public List<UsersBloodRespons> getDoneAppointmentsUser(@PathVariable Long id) {
//        return userService.getDoneAppointmentsUserByBloodBankID(id);
//    }

    @GetMapping("/activate/{code}")
    public String activateAccount(@PathVariable String code) {
        if (activationCodeService.activateAccount(code)) {
            String successMessage = "<div style=\"text-align: center; padding: 20px; background-color: #dff0d8;\">" +
                    "<h1 style=\"font-size: 24px;\">Account Activated Successfully!</h1>" +
                    "<p style=\"font-size: 16px;\">Thank you for activating your account. You can now log in using the link below:</p>" +
                    "<a href=\"http://localhost:4200/login\" style=\"font-size: 18px; color: #007bff; text-decoration: none;\">Login</a>" +
                    "</div>";
            return successMessage;
        } else {
            String errorMessage = "<div style=\"text-align: center; padding: 20px; background-color: #f2dede;\">" +
                    "<h1 style=\"font-size: 24px; color: #d9534f;\">Invalid or Already Activated Code</h1>" +
                    "<p style=\"font-size: 16px;\">The activation code you provided is invalid or has already been used. If you believe this is an error, please contact our support team.</p>" +
                    "</div>";
            return errorMessage;
        }
    }


}