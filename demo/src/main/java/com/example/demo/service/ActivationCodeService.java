package com.example.demo.service;

import com.example.demo.model.ActivationCode;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.repository.ActivationCodeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivationCodeService {

    private final ActivationCodeRepository activationCodeRepository;
    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;

    @Autowired
    public ActivationCodeService(ActivationCodeRepository activationCodeRepository, UserRepository userRepository, EmailServiceImpl emailService) {
        this.activationCodeRepository = activationCodeRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public ActivationCode generateAndSaveCode(String email) {
        String code = UUID.randomUUID().toString();
        ActivationCode activationCode = new ActivationCode(code, email, false);
        return activationCodeRepository.save(activationCode);
    }

    public boolean activateAccount(String code) {
        ActivationCode activationCode = activationCodeRepository.findByCode(code);
        User user = userRepository.findByEmail(activationCode.getEmail());

        if (activationCode != null && !activationCode.isActivated()) {
            activationCode.setActivated(true);
            user.setUserStatus(UserStatus.ACTIVATED);
            activationCodeRepository.save(activationCode);
            emailService.successActivation(user.getEmail());
            return true;
        }

        return false;
    }
}

