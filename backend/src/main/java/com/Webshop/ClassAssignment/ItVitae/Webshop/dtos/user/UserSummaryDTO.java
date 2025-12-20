package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

import java.util.List;

public record UserSummaryDTO(
        Long id,
        String firstName,
        String lastName,
        List<String> roles
) {
    public static UserSummaryDTO fromEntity(User user) {
        return new UserSummaryDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles()
        );
    }
}
