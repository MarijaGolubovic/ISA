package com.example.demo.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // Ovdje možete prilagoditi poruku ili statusni kod u odgovoru koji se šalje klijentu
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Nemate pristup. Molimo provjerite svoje podatke za autentikaciju.");
    }
}

