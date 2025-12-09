package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

import java.util.List;

public record UserCreateDTO(
        String firstName,
        String lastName,
        List<String> roles,
        String email,
        String password,
        String zipCode,
        String houseNumber,
        String streetName
) {
    public User toEntity() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(roles);
        user.setEmail(email);
        user.setPassword(password);
        user.setZipCode(zipCode);
        user.setHouseNumber(houseNumber);
        user.setStreetName(streetName);
        return user;
    }
}
