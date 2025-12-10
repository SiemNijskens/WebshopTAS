package com.Webshop.ClassAssignment.ItVitae.Webshop.services;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserSummaryDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserUpdateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.EmailAlreadyInUseException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.UserNotFoundException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found"));
        return UserDTO.fromEntity(user);
    }

    public List<UserSummaryDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserSummaryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public UserDTO registerUser(UserCreateDTO userCreateDTO) {
        if (userRepository.findByEmail(userCreateDTO.email()).isPresent()) {
            throw new EmailAlreadyInUseException("Something went wrong. Please try again.");
        }

        User user = userCreateDTO.toEntity();
        // TODO: insert "User" role?
        String password = user.getPassword();
        // TODO: implement passwordEncoder
//        String encodedPassword = passwordEncoder.encode(password);
//        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        return UserDTO.fromEntity(savedUser);
    }

    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found"));

        userUpdateDTO.updateEntity(user);
        User savedUser = userRepository.save(user);
        return UserDTO.fromEntity(savedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found"));

        userRepository.delete(user);
    }
}
