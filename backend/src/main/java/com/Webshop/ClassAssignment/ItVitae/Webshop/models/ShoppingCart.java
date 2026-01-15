package com.Webshop.ClassAssignment.ItVitae.Webshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = true)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    private boolean active;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> cartItemList = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }
    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItemList.add(cartItem);
    }
}
