package com.Webshop.ClassAssignment.ItVitae.Webshop.services;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.auth.LoginDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.auth.RegisterDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.EmailAlreadyInUseException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.InvalidCredentialsException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.UserNotFoundException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AuthService(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder,
                       ShoppingCartService shoppingCartService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.shoppingCartService = shoppingCartService;
    }

    public UserDTO registerUser(RegisterDTO registerDTO) {
        if (userRepository.findByEmail(registerDTO.email()).isPresent()) {
            throw new EmailAlreadyInUseException("Something went wrong. Please try again.");
        }

        User user = registerDTO.toEntity();
        user.setRoles(List.of("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        shoppingCartService.createShoppingCart(new ShoppingCartCreateDTO(user.getId()));
        userRepository.save(savedUser);
        return UserDTO.fromEntity(savedUser);
    }


    public UserDTO loginUser(LoginDTO loginDTO) {

        User user = userRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new InvalidCredentialsException("Wrong email or password!"));

        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Wrong email or password!");
        }

        return UserDTO.fromEntity(user);
    }

    public UserDTO getCurrentUser(UserDetails userDetails) {
        // Dev only! Returns admin user when Profile is set to Dev, while no authentication is set.
        if (userDetails == null) {
            User user = userRepository.findByEmail("thomas_webshop@webshop.nl")
                    .orElseThrow();
            return UserDTO.fromEntity(user);
        }

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Not found"));

        return UserDTO.fromEntity(user);
    }
}
