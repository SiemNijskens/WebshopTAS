package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

import java.util.List;

public record ShoppingCartUpdateDTO(
        List<CartItem> cartItems
) {
    public void updateEntity(ShoppingCart shoppingCart) {
        shoppingCart.setCartItemList(this.cartItems);
    }
}
