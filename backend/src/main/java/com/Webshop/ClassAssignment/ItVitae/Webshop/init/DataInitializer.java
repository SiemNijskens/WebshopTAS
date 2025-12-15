package com.Webshop.ClassAssignment.ItVitae.Webshop.init;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.ProductService;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ProductService productService;

    public DataInitializer(UserService userService, UserRepository userRepository, ProductService productService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @PostConstruct
    public void createData() {
        userService.registerUser(new UserCreateDTO("Thomas","Webshop",List.of("ROLE_ADMIN","ROLE_USER"), "thomas_webshop@webshop.nl","password123","2000XX","4A","Javakade","Amersfoort"));
        userService.registerUser(new UserCreateDTO("Siem","Webshop",List.of("ROLE_USER"), "siem_webshop@webshop.nl","password123","3000XX","80","Surinamestraat", "Amersfoort"));
        userService.registerUser(new UserCreateDTO("Stefan","Webshop",List.of("ROLE_USER"), "stefan_webshop@webshop.nl","password123","4000XX","63","Borneolaan", "Amersfoort"));
        userService.registerUser(new UserCreateDTO("Arne","Webshop",List.of("ROLE_ADMIN", "ROLE_USER"), "arne_webshop@webshop.nl","password123","5000XX","419","Curacaogracht", "Amersfoort"));


    }
}
