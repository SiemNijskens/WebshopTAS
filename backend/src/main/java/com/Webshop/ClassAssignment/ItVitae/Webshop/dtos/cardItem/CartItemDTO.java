package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

public record CartItemDTO(
    Long id,
    Product product,
    int amount
) {
    public static CartItemDTO fromEntity(CartItem cartItem) {

        return new CartItemDTO(
                cartItem.getId(),
                cartItem.getProduct(),
                cartItem.getAmount()
        );
    }
}
