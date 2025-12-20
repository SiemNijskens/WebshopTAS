package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

import java.util.List;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        List<String> roles,
        String email,
        String zipCode,
        String houseNumber,
        String streetName,
        String city,
        List<ShoppingCartDTO> shoppingCarts
) {
    public static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRoles(),
                user.getEmail(),
                user.getZipCode(),
                user.getHouseNumber(),
                user.getStreetName(),
                user.getCity(),
                user.getShoppingCarts().stream().map(ShoppingCartDTO::fromEntity)
                        .toList()
        );
    }
}