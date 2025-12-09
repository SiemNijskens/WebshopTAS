package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

public record UserUpdateDTO(
        String firstName,
        String lastName,
        String email,
        String zipCode,
        String houseNumber,
        String streetName
) {
    public void updateEntity(User user) {
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setZipCode(this.zipCode);
        user.setHouseNumber(this.houseNumber);
        user.setStreetName(this.streetName);
    }
}
