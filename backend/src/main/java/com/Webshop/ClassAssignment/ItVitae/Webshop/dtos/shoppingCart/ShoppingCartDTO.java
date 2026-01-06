package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.AttributeType;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

import java.util.List;

public record ShoppingCartDTO(
    Long id,
//    List<Long> cartItemIds
//    User user,
    List<CartItemDTO> cartItems
) {
    public static ShoppingCartDTO fromEntity(ShoppingCart shoppingCart) {
        return new ShoppingCartDTO(
                shoppingCart.getId(),
//                shoppingCart.getCartItemList().stream()
//                        .map(CartItemDTO.fromEntity())
//                shoppingCart.getUser(),
                shoppingCart.getCartItemList().stream()
                        .map(CartItemDTO::fromEntity)
                        .toList()
        );
    }
}
