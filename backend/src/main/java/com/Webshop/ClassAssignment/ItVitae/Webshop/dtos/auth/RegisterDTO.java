package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.auth;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email @NotBlank
        String email,
        @NotBlank
        String password,

        @Size(min=6, max=6) @NotBlank
        String zipCode,
        @NotBlank
        String houseNumber,
        @NotBlank
        String streetName,
        @NotBlank
        String city
) {
    public User toEntity() {
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setZipCode(this.zipCode);
        user.setHouseNumber(this.houseNumber);
        user.setStreetName(this.streetName);
        user.setCity(this.city);
        return user;
    }
}