package com.Webshop.ClassAssignment.ItVitae.Webshop.controllers;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserSummaryDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserUpdateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ShoppingCartRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.AuthService;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.ShoppingCartService;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final ShoppingCartService shoppingCartService;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public UserController(UserService userService, AuthService authService, ShoppingCartService shoppingCartService,
                          UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userService = userService;
        this.authService = authService;
        this.shoppingCartService = shoppingCartService;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserSummaryDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("AUTH = " + auth);
        System.out.println("AUTHORITIES = " + auth.getAuthorities());
        return ResponseEntity.ok(authService.getCurrentUser(userDetails));
    }

    @GetMapping("/me/shoppingcart")
    public ResponseEntity<ShoppingCartDTO> getMyCart(@AuthenticationPrincipal UserDetails userDetails) {
//        ShoppingCart myCart = shoppingCartService.getOrCreateActiveUserCart(user);
        return ResponseEntity.ok(shoppingCartService.getOrCreateMyCart(userDetails));
    }

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserDTO user = this.userService.registerUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        UserDTO updated = userService.updateUser(id, userUpdateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/me/shoppingcart/total-price")
//    public ResponseEntity<ShoppingCartDTO> getTotalPrice(@AuthenticationPrincipal UserDetails userDetails) {
//        ShoppingCart shoppingCart = shoppingCartService
//    }

//    @PostMapping("/me/checkout")
//    public ResponseEntity<ShoppingCartDTO> checkout(@AuthenticationPrincipal UserDetails userDetails) {
//
//    }
}
