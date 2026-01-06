package com.Webshop.ClassAssignment.ItVitae.Webshop.controllers;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartUpdateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> getAllShoppingCarts() {
        List<ShoppingCartDTO> shoppingCartDTOList = shoppingCartService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable Long id) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartDTO);
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> createShoppingCart(@RequestBody ShoppingCartCreateDTO shoppingCartCreateDTO) {
        ShoppingCartDTO newShoppingCartDTO = shoppingCartService.createShoppingCart(shoppingCartCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newShoppingCartDTO);
    }

    @PutMapping
    public ResponseEntity<ShoppingCartDTO> updateShoppingCart(@PathVariable ShoppingCartUpdateDTO shoppingCartUpdateDTO) {
        ShoppingCartDTO updatedShoppingCart = shoppingCartService.updateShoppingCart(shoppingCartUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedShoppingCart);
    }



}
