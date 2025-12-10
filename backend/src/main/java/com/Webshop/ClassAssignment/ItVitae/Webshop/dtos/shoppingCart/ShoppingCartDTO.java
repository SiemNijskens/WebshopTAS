package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

import java.util.List;

public record ShoppingCartDTO(
    Long id,
    User user,
    List<CartItem> cartItems
) {
    public static ShoppingCartDTO fromEntity(ShoppingCart shoppingCart) {
        return new ShoppingCartDTO(
                shoppingCart.getId(),
                shoppingCart.getUser(),
                shoppingCart.getCartItemList()
        );
    }
}
