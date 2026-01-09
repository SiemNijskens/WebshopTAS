package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonAlias;

public record ShoppingCartCreateDTO(
//        @JsonAlias("user_id")
//        Long userId

) {
    public ShoppingCart toEntity() {
        return new ShoppingCart();
    }
}
