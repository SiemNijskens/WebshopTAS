package com.Webshop.ClassAssignment.ItVitae.Webshop.services;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.cardItem.CartItemCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartUpdateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.CartItemNotFoundException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.ProductNotFoundException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.ShoppingCartNotFoundException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.UserNotFoundException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.CartItem;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.ShoppingCart;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.User;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.CartItemRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ProductRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ShoppingCartRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartService(
            ShoppingCartRepository shoppingCartRepository, UserRepository userRepository,
            CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public List<ShoppingCartDTO> findAll() {
        return shoppingCartRepository.findAll().stream().map(ShoppingCartDTO::fromEntity).toList();
    }

    public ShoppingCartDTO findById(Long id) {
        System.out.println("[ShoppingCartService] findById called with cartId=" + id);
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Entity not found"));
        return ShoppingCartDTO.fromEntity(shoppingCart);
    }

    public ShoppingCart createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setActive(true);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart createGuestCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setActive(true);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getOrCreateActiveUserCart (User user) {
        return shoppingCartRepository
                .findByUserAndActiveTrue(user)
                .orElseGet(() -> createShoppingCart(user));
    }

    public ShoppingCartDTO attachUserToCart(Long shoppingCartId, Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException("Cart not found with ID: "));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: "));

        shoppingCart.setUser(user);
        shoppingCart.setActive(true);
        ShoppingCart savedCart = shoppingCartRepository.save(shoppingCart);

        return ShoppingCartDTO.fromEntity(savedCart);
    }

    public ShoppingCartDTO updateShoppingCart(ShoppingCartUpdateDTO shoppingCartUpdateDTO) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartUpdateDTO.id()).orElseThrow(() -> new RuntimeException("Entity not found"));
        shoppingCartUpdateDTO.updateEntity(shoppingCart);
        ShoppingCart updatedShoppingCart = shoppingCartRepository.save(shoppingCart);
        return ShoppingCartDTO.fromEntity(shoppingCart);
    }

    public ShoppingCartDTO addCartItem(Long shoppingCartId, CartItemCreateDTO cartItemCreateDTO) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException("ShoppingCart with ID: " + shoppingCartId + " not found."));

        Product product = productRepository.findById(cartItemCreateDTO.productId())
                .orElseThrow(() -> new ProductNotFoundException("Product with ID: " + cartItemCreateDTO.productId() + " not found."));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setAmount(cartItemCreateDTO.amount());
        cartItem.setShoppingCart(shoppingCart);

        shoppingCart.addCartItem(cartItem);
        cartItemRepository.save(cartItem);
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);
        return ShoppingCartDTO.fromEntity(savedShoppingCart);
    }

    public ShoppingCartDTO getOrCreateMyCart(UserDetails userDetails) {
        if (userDetails == null) {
            User user = userRepository.findByEmail("thomas_webshop@webshop.nl")
                    .orElseThrow(() -> new UserNotFoundException("Dev not found"));
            ShoppingCart cart = getOrCreateActiveUserCart(user);
            return ShoppingCartDTO.fromEntity(cart);
        }

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Not found"));

        ShoppingCart cart = getOrCreateActiveUserCart(user);

        return ShoppingCartDTO.fromEntity(cart);
//                .findByUserAndActiveTrue(user)
//                .map(ShoppingCartDTO::fromEntity)
//                .orElse(null);
    }

    public ShoppingCart mergeGuestIntoUserCart(Long guestCartId, UserDetails userDetails) {
        User user;

        if (userDetails == null) {
            System.out.println("[DEV MODE] userDetails is null, using default dev user");
            user = userRepository.findByEmail("thomas_webshop@webshop.nl")
                    .orElseThrow(() -> new UserNotFoundException("Dev user not found"));
//            return null;
//            throw new RuntimeException("Cannot merge guest cart: user is not logged in");
        } else {
            user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UserNotFoundException("Not found"));
        }

        ShoppingCart guestCart = shoppingCartRepository
                .findByIdAndActiveTrue(guestCartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException("Guest Cart not found with ID: " + guestCartId));

        ShoppingCart userCart = shoppingCartRepository.findByUserAndActiveTrue(user)
                .orElseGet(() -> createShoppingCart(user));

        guestCart.getCartItemList().forEach(item -> {
            item.setShoppingCart(userCart);
            userCart.addCartItem(item);
        });

        guestCart.getCartItemList().clear();
        guestCart.setActive(false);
//        for (CartItem guestItem: guestCart.getCartItemList()) {
//
//            Optional<CartItem> existingItem = userCart.getCartItemList().stream()
//                    .filter(item -> item.getProduct().getId()
//                            .equals(guestItem.getProduct().getId()))
//                    .findFirst();
//
//            if (existingItem.isPresent()) {
//                existingItem.get().setAmount(
//                        existingItem.get().getAmount() + guestItem.getAmount()
//                );
//            } else {
//                guestItem.setShoppingCart(userCart);
//                userCart.getCartItemList().add(guestItem);
//            }
//        }
//        guestCart.setActive(false);

        shoppingCartRepository.save(userCart);
        shoppingCartRepository.save(guestCart);

        return userCart;
    }

    public ShoppingCartDTO mergeCarts(Long guestCartId, Long userCartId) {
        ShoppingCart guestCart = shoppingCartRepository.findById(guestCartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException("Guest Cart not found with ID: " + guestCartId));

        ShoppingCart userCart = shoppingCartRepository.findById(userCartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException("User Cart not found with ID: " + userCartId));

        guestCart.getCartItemList().forEach(item -> {
            item.setShoppingCart(userCart);
            userCart.addCartItem(item);
        });
        guestCart.setActive(false);
        guestCart.getCartItemList().clear();

        shoppingCartRepository.save(userCart);
        shoppingCartRepository.save(guestCart);

        return ShoppingCartDTO.fromEntity(userCart);
    }
}
