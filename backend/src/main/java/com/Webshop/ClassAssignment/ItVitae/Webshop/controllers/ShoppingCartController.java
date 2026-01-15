package com.Webshop.ClassAssignment.ItVitae.Webshop.controllers;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartUpdateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final UserRepository userRepository;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, UserRepository userRepository) {
        this.shoppingCartService = shoppingCartService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> getAllShoppingCarts() {
        List<ShoppingCartDTO> shoppingCartDTOList = shoppingCartService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable Long id) {
        ShoppingCartDTO shoppingCart = shoppingCartService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> createGuestCart() {
        ShoppingCart shoppingCart = shoppingCartService.createGuestCart();
        return ResponseEntity.status(HttpStatus.CREATED).body(ShoppingCartDTO.fromEntity(shoppingCart));
    }

    @PutMapping
    public ResponseEntity<ShoppingCartDTO> updateShoppingCart(@PathVariable ShoppingCartUpdateDTO shoppingCartUpdateDTO) {
        ShoppingCartDTO updatedShoppingCart = shoppingCartService.updateShoppingCart(shoppingCartUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedShoppingCart);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<ShoppingCartDTO> addItem(
            @PathVariable("cartId") Long shoppingCartId,
            @RequestBody CartItemCreateDTO cartItemCreateDTO) {
        ShoppingCartDTO shoppingCart = shoppingCartService.addCartItem(shoppingCartId, cartItemCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
    }

    @PostMapping("/{cartId}/attach-user/{userId}")
    public ResponseEntity<ShoppingCartDTO> attachUserToCart(
            @PathVariable Long cartId,
            @PathVariable Long userId) {
        System.out.println("[ATTACH USER] cartId=" + cartId + " userId=" + userId);
        ShoppingCartDTO attachedCart = shoppingCartService.attachUserToCart(cartId, userId);
        System.out.println("[ATTACH USER] returning cart with id=" + attachedCart.id() + " and items=" + attachedCart.cartItems().size());
        return ResponseEntity.status(HttpStatus.OK).body(attachedCart);
    }

    @PostMapping("/merge/{guestCartId}")
    public ResponseEntity<ShoppingCartDTO> mergeGuestCart(
            @PathVariable Long guestCartId,
            @AuthenticationPrincipal UserDetails userDetails) {
        ShoppingCart mergedCart = shoppingCartService.mergeGuestIntoUserCart(guestCartId, userDetails);
        return ResponseEntity.ok(ShoppingCartDTO.fromEntity(mergedCart));
    }
}
