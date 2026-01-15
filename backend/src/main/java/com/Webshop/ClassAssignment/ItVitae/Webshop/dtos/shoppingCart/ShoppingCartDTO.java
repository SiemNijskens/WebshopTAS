package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserSummaryDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.AttributeType;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;

import java.util.List;

public record ShoppingCartDTO(
    Long id,
    UserSummaryDTO user,
    List<CartItemDTO> cartItems,
    float totalPrice
) {
    public static ShoppingCartDTO fromEntity(ShoppingCart shoppingCart) {
        float totalPrice = shoppingCart.getCartItemList().stream()
                .map(item ->
                        item.getProduct().getPrice()
                        * item.getAmount()
                        * item.getProduct().getSalePercentage()
                )
                .reduce(0F, Float::sum);

        return new ShoppingCartDTO(
                shoppingCart.getId(),
                shoppingCart.getUser() != null
                ? UserSummaryDTO.fromEntity(shoppingCart.getUser())
                : null,
                shoppingCart.getCartItemList().stream()
                        .map(CartItemDTO::fromEntity)
                        .toList(),
                totalPrice
        );
    }
}
