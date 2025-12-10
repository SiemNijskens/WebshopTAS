package com.Webshop.ClassAssignment.ItVitae.Webshop.services;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ShoppingCartRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }

    public List<ShoppingCartDTO> findAll() {
        return shoppingCartRepository.findAll().stream().map(ShoppingCartDTO::fromEntity).toList();
    }

    public ShoppingCartDTO findById(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        return ShoppingCartDTO.fromEntity(shoppingCart);
    }

    @Transactional
    public ShoppingCartDTO createShoppingCart(ShoppingCartCreateDTO shoppingCartCreateDTO) {
        User user = userRepository.findById(shoppingCartCreateDTO.userId()).orElseThrow(() -> new RuntimeException("Entity not found"));
        ShoppingCart shoppingCart = shoppingCartCreateDTO.toEntity();
        user.addShoppingCart(shoppingCart);
        shoppingCart.setUser(user);
        ShoppingCart savedCart = shoppingCartRepository.save(shoppingCart);
        return ShoppingCartDTO.fromEntity(savedCart);
    }
}
