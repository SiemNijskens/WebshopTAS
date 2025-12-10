package com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String message) {
        super(message);
    }
}
