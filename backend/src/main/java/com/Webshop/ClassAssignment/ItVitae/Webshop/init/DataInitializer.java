package com.Webshop.ClassAssignment.ItVitae.Webshop.init;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.AttributeType;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.ProductService;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        List<String> sizes = List.of("S","M","L");
        List<String> colors = List.of("Red", "White", "Blue", "Green");

        List<ProductCreateDTO> products = new ArrayList<>();

        for (String size : sizes) {
            for (String color : colors ) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        19.99f,
                        1.0f,
                        10,
                        color,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Size", size, AttributeType.VARIANT)
                        )
                );
                products.add((variant));
            }
        }

        List<ProductAttributeCreateDTO> baseAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Material","Cotton", AttributeType.PRODUCT),
                new ProductAttributeCreateDTO(null, "Fit","Slim Fit", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO tShirtBase = new ProductBaseCreateDTO(
                "TS-100",
                "Basic T-Shirt",
                "100% Cotton",
                "Generic",
                products,
                baseAttributes
        );
        ProductBaseDTO savedBase = productService.createProduct(tShirtBase);

        List<String> pantsSizes = List.of("28:30","30:32","32:34");
        List<String> pantsColors = List.of("Black", "Gray", "Blue", "Khaki");

        List<ProductCreateDTO> variants = new ArrayList<>();

        for (String pantsSize : sizes) {
            for (String pantsColor : pantsColors ) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        39.99f,
                        1.0f,
                        20,
                        pantsColor,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Size", pantsSize, AttributeType.VARIANT)
                        )
                );
                variants.add((variant));
            }
        }

        List<ProductAttributeCreateDTO> attributes = List.of(
                new ProductAttributeCreateDTO(null,"Material","Denim", AttributeType.PRODUCT),
                new ProductAttributeCreateDTO(null, "Fit","Slim Fit", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO pantsBase = new ProductBaseCreateDTO(
                "P-200",
                "Basic Pants",
                "100% Denim",
                "Generic",
                variants,
                attributes
        );
        ProductBaseDTO savedPants = productService.createProduct(pantsBase);
    }
}
