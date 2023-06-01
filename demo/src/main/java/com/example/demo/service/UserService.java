package com.example.demo.service;

import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UsersBloodRespons;
import com.example.demo.model.ActivationCode;
import com.example.demo.model.Appointment;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.AppointmentRepository;

import com.example.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository UserRepository;
    private final AddressRepository AddressRepository;
    private final BloodBankRepository BloodBankRepository;
    private final AppointmentRepository appRepo;
    private final ActivationCodeService activationCodeService;
    private final EmailServiceImpl emailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, BloodBankRepository bloodBankRepository, AppointmentRepository appointmentRepository, ActivationCodeService activationCodeService, EmailServiceImpl emailService){
        this.UserRepository = userRepository;
        this.AddressRepository = addressRepository;
        this.BloodBankRepository = bloodBankRepository;
		this.appRepo = appointmentRepository;
        this.activationCodeService = activationCodeService;
        this.emailService = emailService;
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
        String encodedPassword = passwordEncoder().encode(u.getPassword());
        u.setPassword(encodedPassword);
        ActivationCode activationCode = activationCodeService.generateAndSaveCode(u.getEmail());
        emailService.sendActivationEmail(u.getEmail(), activationCode.getCode());

        u.setUserStatus(UserStatus.NOT_ACTIVATED);
        u.setURN("123456789012");
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
    
	public List<UsersBloodRespons> getDoneAppointmentsUserByBloodBankID(Long id) {
		List<Appointment> apps = appRepo.getDoneAppointmentsByBloodBankID(id);
		List<UsersBloodRespons> users = new ArrayList<UsersBloodRespons>();
		User user = new User();
		for (Appointment app : apps) {
			user = getById(app.getUser().getId());
			users.add(new UsersBloodRespons(user.getName(), user.getSurname(), user.getEmail(), app.getDate(), app.getTime()));
		}
		return users;
	}

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Dohvatanje korisničkog imena
        User user = getByEmail(username);
        System.out.print("=============================="+user.getEmail()+"\n");
        // Možete dohvatiti više informacija kao što su role, authorities, itd.

        return user;
    }

}