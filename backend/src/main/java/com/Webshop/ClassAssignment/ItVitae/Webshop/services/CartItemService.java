package com.Webshop.ClassAssignment.ItVitae.Webshop.services;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemUpdateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class    CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItemDTO findById(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Enitity not found"));
        return CartItemDTO.fromEntity(cartItem);
    }

    public List<CartItemDTO> findAll() {
        return cartItemRepository.findAll().stream().map(CartItemDTO::fromEntity).toList();
    }

    public CartItemDTO createCartItem(CartItemCreateDTO cartItemCreateDTO) {
        CartItem savedItem =cartItemRepository.save(cartItemCreateDTO.toEntity());
        return CartItemDTO.fromEntity(savedItem);
    }

    public CartItemDTO updateCartItem(Long id, CartItemUpdateDTO cartItemUpdateDTO) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        cartItemUpdateDTO.updateEntity(cartItem);
        CartItem savedItem = cartItemRepository.save(cartItem);
        return CartItemDTO.fromEntity(savedItem);
    }

    public void deleteCartItem(Long id){
        cartItemRepository.deleteById(id);
    }

    @Transactional
    public void addCartItemToShoppingCart(CartItemCreateDTO cartItemCreateDTO) {
    // die shoppingCart bestaat nog niet
        // get product by id
        // get shoppingcart by id
        CartItem newCartItem = cartItemCreateDTO.toEntity();
        // cartitem.setshoppingcart
        // shoppingcart.addCartItem
    }
}
