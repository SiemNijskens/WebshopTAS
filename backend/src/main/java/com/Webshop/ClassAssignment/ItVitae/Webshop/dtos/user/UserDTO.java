package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

import java.util.List;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        List<String> roles
) {
    public static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles()
        );
    }
}