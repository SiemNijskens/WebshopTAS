package com.Webshop.ClassAssignment.ItVitae.Webshop.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "shoppingCart")
    private List<CartItem> cartItemList;
}
