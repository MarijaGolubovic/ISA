package com.example.demo.service;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.BloodBank;
import com.example.demo.model.User;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository UserRepository;
    private final AddressRepository AddressRepository;
    private final BloodBankRepository BloodBankRepository;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, BloodBankRepository bloodBankRepository){
        this.UserRepository = userRepository;
        this.AddressRepository = addressRepository;
        this.BloodBankRepository = bloodBankRepository;

    }

    public List<User> getAllUsers(){
        return UserRepository.findAll();
    }

    public List<UserResponse> getAllUserResponses(){
        return UserRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getAllUsersResponseForNameAndSurname(String name, String surname){
        return UserRepository.findByNameAndSurname(name,surname)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserResponse convertEntityToDto(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());

        return userResponse;
    }
    
    public void saveUser(User u) {
        this.BloodBankRepository.save(u.getBloodBank());
        this.AddressRepository.save(u.getAddress());
    	this.UserRepository.save(u);
    }
    
    public void registerUser(User u) {
        this.AddressRepository.save(u.getAddress());
    	this.UserRepository.save(u);
    }



}