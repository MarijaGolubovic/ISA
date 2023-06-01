package com.example.demo.jwt;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Konvertujte UserType u ovlasti (GrantedAuthority objekte)
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + user.getUserType())
        );
        // Kreirajte instancu CustomUserDetails sa email-om, password-om i authorities
        return new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

//    @Override
//    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//
//        // Konvertujte UserType u ovlasti (GrantedAuthority objekte)
//        Collection<? extends GrantedAuthority> authorities = Collections.singleton(
//                new SimpleGrantedAuthority("ROLE_" + user.getUserType())
//        );
//        // Kreirajte instancu CustomUserDetails sa email-om, nekodiranom lozinkom i authorities
//        return new CustomUserDetails(
//                user.getEmail(),
//                user.getPassword(), // Ovdje se koristi nekodirana lozinka iz baze
//                authorities
//        );
//    }


}

