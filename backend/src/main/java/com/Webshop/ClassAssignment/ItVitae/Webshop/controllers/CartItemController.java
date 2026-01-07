package com.Webshop.ClassAssignment.ItVitae.Webshop.controllers;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemUpdateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cartitems")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> createCartItem(@RequestBody CartItemCreateDTO cartItemCreateDTO) {
        CartItemDTO newCartItem = cartItemService.createCartItem(cartItemCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCartItem);
    }

    @PostMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCartDTO> addCartItemToShoppingCart(
            @RequestBody CartItemCreateDTO cartItemCreateDTO,
            @PathVariable Long shoppingCartId) {
        ShoppingCartDTO shoppingCartDTO = cartItemService.addCartItemToShoppingCart(cartItemCreateDTO, shoppingCartId);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartDTO);
    }

    //voor testing purposes
    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getAllCartItems() {
        List<CartItemDTO> cartItemDTOList = cartItemService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cartItemDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> getCartItemById(@PathVariable Long id) {
        CartItemDTO cartItem = cartItemService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDTO> putCartItem(@PathVariable Long id, CartItemUpdateDTO cartItemUpdateDTO) {
        CartItemDTO updatedCartItem = cartItemService.updateCartItem(id, cartItemUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok("CartItem deleted");
    }
}
