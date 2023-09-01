package com.example.demo.jwt;

import com.example.demo.model.User;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;


    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        System.out.print("=============================="+authRequest.getUsername()+ authRequest.getPassword()+"\n");
        User user = userRepository.findByEmail(authRequest.getUsername());
        if(user.getUserStatus() == UserStatus.NOT_ACTIVATED){
            return ResponseEntity.badRequest().build();
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        System.out.print("=============================="+"\n");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.print("**************************"+authentication.getDetails()+"\n");
        String jwt = jwtTokenProvider.generateToken(authentication);
        System.out.print("**************************"+jwt+"\n");
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
