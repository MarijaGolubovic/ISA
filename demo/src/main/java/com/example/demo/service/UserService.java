package com.example.demo.service;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.BloodBank;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public User getByEmail(String email){return UserRepository.findByEmail(email);}

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
        if (u.getAddress() != null){
            this.AddressRepository.save(u.getAddress());
        }
        if (u.getBloodBank() != null){
            this.BloodBankRepository.save(u.getBloodBank());
        }
    	  this.UserRepository.save(u);
    }

    public List<UserResponse> getAllUsersForAdminCenter(User admin){
        if (admin.getUserType() == UserType.ADMIN_SISTEM){
            return getAllUserResponses();
        }
        return this.UserRepository.findByBloodBank(admin.getBloodBank().getId())
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
    
    public void registerUser(User u) {
        this.AddressRepository.save(u.getAddress());
    	this.UserRepository.save(u);
    }


    public List<User> getCentersAdmins (Long idCenter) {
        return this.UserRepository.findByCenterID(idCenter);
    }

    public List<UserResponse> getAdminCentersWithoutBB(){
        return UserRepository.findAdminCentersWithoutBB()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public void update(UserResponse u, String id){
        User user = UserRepository.findByEmail(u.getEmail());
        user.setBloodBank(BloodBankRepository.findByID(Long.valueOf(id)));
        UserRepository.save(user);
    }
    
    public void updateUser(User user){
        UserRepository.save(user);
    }
    
    public User getById(Long id){
        return UserRepository.getOne(id);
    }

}