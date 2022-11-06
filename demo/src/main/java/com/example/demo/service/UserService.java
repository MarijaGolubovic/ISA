package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository UserRepository;

    @Autowired
    public UserService(UserRepository UserRepository){
        this.UserRepository = UserRepository;
    }

    public List<User> getAllUsers(){
        return UserRepository.findAll();
    }

}