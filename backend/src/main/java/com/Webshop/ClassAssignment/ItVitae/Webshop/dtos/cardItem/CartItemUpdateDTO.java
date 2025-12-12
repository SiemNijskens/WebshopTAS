package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
//import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

public record CartItemUpdateDTO(
//        Product product,
        int amount
) {
    public void updateEntity(CartItem cartItem) {
//        cartItem.setProduct(this.product);
        cartItem.setAmount(this.amount);
    }
}
