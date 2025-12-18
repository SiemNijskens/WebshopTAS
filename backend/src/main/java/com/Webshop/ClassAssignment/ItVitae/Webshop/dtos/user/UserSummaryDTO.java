package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

import java.util.List;

public record UserSummaryDTO(
        String firstName,
        String lastName,
        List<String> roles
) {
    public static UserSummaryDTO fromEntity(User user) {
        return new UserSummaryDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getRoles()
        );
    }
}
