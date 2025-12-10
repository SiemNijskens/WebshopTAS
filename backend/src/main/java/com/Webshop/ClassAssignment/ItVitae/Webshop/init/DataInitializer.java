package com.Webshop.ClassAssignment.ItVitae.Webshop.init;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final UserService userService;
    private final UserRepository userRepository;

    public DataInitializer(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createData() {
        userService.registerUser(new UserCreateDTO("Thomas","Webshop",List.of("ROLE_ADMIN","ROLE_USER"), "thomas_webshop@webshop.nl","password123","2000XX","4A","Javakade"));
        userService.registerUser(new UserCreateDTO("Siem","Webshop",List.of("ROLE_USER"), "siem_webshop@webshop.nl","password123","3000XX","80","Surinamekade"));
    }
}
