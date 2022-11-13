package com.example.demo.service;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
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

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository){
        this.UserRepository = userRepository;
        this.AddressRepository = addressRepository;
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
    	this.UserRepository.save(u);
    	this.AddressRepository.save(u.getAddress());
    }

    public List<User> getCentersAdmins (Long idCenter) {
        return this.UserRepository.findByCenterID(idCenter);
    }

}