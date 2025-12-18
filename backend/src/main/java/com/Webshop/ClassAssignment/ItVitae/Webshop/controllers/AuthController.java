package com.Webshop.ClassAssignment.ItVitae.Webshop.controllers;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.auth.LoginDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.auth.RegisterDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.AuthService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        UserDTO user = authService.registerUser(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(
            @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.loginUser(loginDTO));
    }
}
