package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductSummaryDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;

public record CartItemDTO(
    Long id,
    ProductSummaryDTO product,
    int amount
) {
    public static CartItemDTO fromEntity(CartItem cartItem) {

        return new CartItemDTO(
                cartItem.getId(),
                ProductSummaryDTO.fromEntity(cartItem.getProduct()),
                cartItem.getAmount()
        );
    }
}
