package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserCreateDTO(
        String firstName,
        String lastName,
        List<String> roles,
        String email,
        String password,

        @Size(min=6, max=6)
        String zipCode,
        String houseNumber,
        String streetName,
        String city
) {
    public User toEntity() {
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setRoles(this.roles);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setZipCode(this.zipCode);
        user.setHouseNumber(this.houseNumber);
        user.setStreetName(this.streetName);
        user.setCity(this.city);
        return user;
    }
}
