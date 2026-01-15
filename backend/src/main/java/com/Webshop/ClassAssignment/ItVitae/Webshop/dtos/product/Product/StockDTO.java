package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product;

import jakarta.validation.constraints.PositiveOrZero;

public record StockDTO(
        Long variantId,
        @PositiveOrZero
        int newStock
) {
}
