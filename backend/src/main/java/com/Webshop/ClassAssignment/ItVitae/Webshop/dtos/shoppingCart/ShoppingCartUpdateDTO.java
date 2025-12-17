package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;

import java.util.List;

public record ShoppingCartUpdateDTO(
        Long id,
        List<CartItem> cartItems
) {
    public void updateEntity(ShoppingCart shoppingCart) {
        shoppingCart.setCartItemList(this.cartItems);
    }
}
