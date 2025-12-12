package com.Webshop.ClassAssignment.ItVitae.Webshop.services;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemUpdateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.CartItemRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ProductRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class    CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public CartItemDTO findById(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Enitity not found"));
        return CartItemDTO.fromEntity(cartItem);
    }

    public List<CartItemDTO> findAll() {
        return cartItemRepository.findAll().stream().map(CartItemDTO::fromEntity).toList();
    }

    public CartItemDTO createCartItem(CartItemCreateDTO cartItemCreateDTO) {
        Product product = productRepository.findById(cartItemCreateDTO.productId()).orElseThrow(() -> new RuntimeException("Entity not found"));
        CartItem cartItem = cartItemCreateDTO.toEntity();
        cartItem.setProduct(product);
        CartItem savedItem = cartItemRepository.save(cartItem);
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
    public ShoppingCartDTO addCartItemToShoppingCart(CartItemCreateDTO cartItemCreateDTO, Long shoppingCartId) {
        Product product = productRepository.findById(cartItemCreateDTO.productId()).orElseThrow(() -> new RuntimeException("Entity not found"));
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(() -> new RuntimeException("Entity not found"));
        CartItem newCartItem = cartItemCreateDTO.toEntity();
        newCartItem.setProduct(product);
        newCartItem.setShoppingCart(shoppingCart);
        shoppingCart.addCartItem(newCartItem);
        cartItemRepository.save(newCartItem);
        shoppingCartRepository.save(shoppingCart);
        return ShoppingCartDTO.fromEntity(shoppingCart);
    }
}
