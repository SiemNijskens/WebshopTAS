package com.Webshop.ClassAssignment.ItVitae.Webshop.controllers;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.auth.LoginDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.auth.RegisterDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.InvalidCredentialsException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
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
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager,
                          UserRepository userRepository) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        UserDTO user = authService.registerUser(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(
            @RequestBody LoginDTO loginDTO,
            HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.password()
                )
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        request.getSession(true)
                .setAttribute(
                        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        context
                );

        User user = userRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        return ResponseEntity.ok(UserDTO.fromEntity(user));
//        return ResponseEntity.ok(authService.loginUser(loginDTO));
    }
}
