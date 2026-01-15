package com.Webshop.ClassAssignment.ItVitae.Webshop.init;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.shoppingCart.ShoppingCartCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.user.UserCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.AttributeType;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.UserRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.ProductService;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.ShoppingCartService;
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
    private final ShoppingCartService shoppingCartService;

    public DataInitializer(UserService userService, UserRepository userRepository, ProductService productService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    public void createData() {
        userService.registerUser(new UserCreateDTO("Thomas","Webshop",List.of("ADMIN","USER"), "thomas_webshop@webshop.nl","password123","2000XX","4A","Javakade","Amersfoort"));
        userService.registerUser(new UserCreateDTO("Siem","Webshop",List.of("USER"), "siem_webshop@webshop.nl","password123","3000XX","80","Surinamestraat", "Amersfoort"));
        userService.registerUser(new UserCreateDTO("Stefan","Webshop",List.of("USER"), "stefan_webshop@webshop.nl","password123","4000XX","63","Borneolaan", "Amersfoort"));
        userService.registerUser(new UserCreateDTO("Arne","Webshop",List.of("ADMIN", "USER"), "arne_webshop@webshop.nl","password123","5000XX","419","Curacaogracht", "Amersfoort"));

//        shoppingCartService.createShoppingCart(new ShoppingCartCreateDTO(1L));
//        shoppingCartService.createShoppingCart(new ShoppingCartCreateDTO(2L));
//        shoppingCartService.createShoppingCart(new ShoppingCartCreateDTO(3L));
//        shoppingCartService.createShoppingCart(new ShoppingCartCreateDTO(4L));

        List<String> shirtSizes = List.of("S","M","L");
        List<String> shirtColors = List.of("White", "Blue", "Orange", "Black", "Red");

        List<ProductCreateDTO> feestShirts = new ArrayList<>();

        for (String size : shirtSizes) {
            for (String color : shirtColors ) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        "/images/products/generic_tshirt_print_HetIsFeest_" + color.toLowerCase() + ".png",
                        19.99f,
                        1.0f,
                        10,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Size", size, AttributeType.VARIANT),
                                new ProductAttributeCreateDTO(null, "Color", color, AttributeType.VARIANT)
                        )
                );
                feestShirts.add((variant));
            }
        }

        List<ProductAttributeCreateDTO> shirtAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Material","Cotton", AttributeType.PRODUCT),
                new ProductAttributeCreateDTO(null, "Fit","Slim Fit", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO tShirtBase = new ProductBaseCreateDTO(
                "TS-100",
                "/images/products/generic_tshirt_print_HetIsFeest_white.png",
                "T-Shirts",
                "Basic T-Shirt",
                "100% Cotton",
                "Generic",
                feestShirts,
                shirtAttributes
        );
        ProductBaseDTO savedShirt = productService.createProduct(tShirtBase);


        List<String> webShirtSizes = List.of("S","M","L");
        List<String> webShirtColors = List.of("White");

        List<ProductCreateDTO> webShirts = new ArrayList<>();

        for (String webShirtSize : webShirtSizes) {
            for (String webShirtColor : webShirtColors ) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        "/images/products/generic_tshirt_print_Webshop_" + webShirtColor.toLowerCase() + ".png",
                        29.99f,
                        1.0f,
                        10,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Size", webShirtSize, AttributeType.VARIANT),
                                new ProductAttributeCreateDTO(null, "Color", webShirtColor, AttributeType.VARIANT)
                        )
                );
                webShirts.add((variant));
            }
        }

        List<ProductAttributeCreateDTO> webShirtAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Material","Cotton", AttributeType.PRODUCT),
                new ProductAttributeCreateDTO(null, "Fit","Slim Fit", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO webShirtBase = new ProductBaseCreateDTO(
                "TS-200",
                "/images/products/generic_tshirt_print_Webshop_white.png",
                "T-Shirts",
                "Webshop T-Shirt",
                "100% Cotton",
                "Webshop",
                webShirts,
                webShirtAttributes
        );
        ProductBaseDTO savedWebShirt = productService.createProduct(webShirtBase);


        List<String> pantsSizes = List.of("28:30","30:32","32:34");
        List<String> pantsColors = List.of("Black", "Gray", "Blue", "Khaki");

        List<ProductCreateDTO> pants = new ArrayList<>();

        for (String pantsSize : pantsSizes) {
            for (String pantsColor : pantsColors ) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        "/images/products/basic_pants_" + pantsColor.toLowerCase() + ".png",
                        39.99f,
                        0.7f,
                        20,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Size", pantsSize, AttributeType.VARIANT),
                                new ProductAttributeCreateDTO(null, "Color", pantsColor, AttributeType.VARIANT)
                        )
                );
                pants.add((variant));
            }
        }

        List<ProductAttributeCreateDTO> pantsAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Material","Denim", AttributeType.PRODUCT),
                new ProductAttributeCreateDTO(null, "Fit","Slim Fit", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO pantsBase = new ProductBaseCreateDTO(
                "P-200",
                "/images/products/basic_pants_black.png",
                "Pants",
                "Basic Pants",
                "100% Denim",
                "Billie Jeans",
                pants,
                pantsAttributes
        );
        ProductBaseDTO savedPants = productService.createProduct(pantsBase);


        List<String> hoodiesSizes = List.of("M","L");
        List<String> hoodiesColors = List.of("Black", "Gray");

        List<ProductCreateDTO> hoodies = new ArrayList<>();

        for (String hoodieSize : hoodiesSizes) {
            for (String hoodieColor : hoodiesColors ) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        "/images/products/fancy_hoodie_" + hoodieColor.toLowerCase() + ".png",
                        24.99f,
                        1.0f,
                        5,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Size", hoodieSize, AttributeType.VARIANT),
                                new ProductAttributeCreateDTO(null, "Color", hoodieColor, AttributeType.VARIANT)
                        )
                );
                hoodies.add((variant));
            }
        }

        List<ProductAttributeCreateDTO> hoodieAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Material","Wool", AttributeType.PRODUCT),
                new ProductAttributeCreateDTO(null, "Hood","Adjustable", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO hoodiesBase = new ProductBaseCreateDTO(
                "H-50",
                "/images/products/fancy_hoodie_black.png",
                "Hoodies",
                "Fancy Hoodie",
                "Now with elastic cord to adjust your hood. Also with long sleeves.",
                "In Da Hood",
                hoodies,
                hoodieAttributes
        );
        ProductBaseDTO savedHoodies = productService.createProduct(hoodiesBase);


        List<String> capsSizes = List.of("M");
        List<String> capsColors = List.of("Black", "Blue");

        List<ProductCreateDTO> caps = new ArrayList<>();

        for (String capSize : capsSizes) {
            for (String capColor : capsColors ) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        "/images/products/cool_cap_" + capColor.toLowerCase() + ".png",
                        14.99f,
                        1.0f,
                        5,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Size", capSize, AttributeType.VARIANT),
                                new ProductAttributeCreateDTO(null, "Color", capColor, AttributeType.VARIANT)
                        )
                );
                caps.add((variant));
            }
        }

        List<ProductAttributeCreateDTO> capAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Material","Gore-Tex", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO capsBase = new ProductBaseCreateDTO(
                "C-20",
                "/images/products/cool_cap_black.png",
                "Caps",
                "Cool Cap",
                "The best Cap to look slick.",
                "Generic",
                caps,
                capAttributes
        );
        ProductBaseDTO savedCaps = productService.createProduct(capsBase);


        List<String> sockSizes = List.of("XS","S","M","L","XL");

        List<ProductCreateDTO> socks = new ArrayList<>();

        for (String sockSize : sockSizes) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        "/images/products/gramfel_socks_white.png",
                        5.99f,
                        1.0f,
                        30,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Size", sockSize, AttributeType.VARIANT)
                        )
                );
                socks.add((variant));
        }

        List<ProductAttributeCreateDTO> sockAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Material","Wool", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO socksBase = new ProductBaseCreateDTO(
                "S-20",
                "/images/products/gramfel_socks_white.png",
                "Socks",
                "Gramfel socks",
                "Gramfel socks for your feet.",
                "Gramfel",
                socks,
                sockAttributes
        );
        ProductBaseDTO savedSocks = productService.createProduct(socksBase);


        List<String> cheeseCountries = List.of("Italy","France", "Switzerland");
        List<String> cheeseColors = List.of("Yellow", "Blue","White");

        List<ProductCreateDTO> cheeses = new ArrayList<>();

        for (String cheeseCountry : cheeseCountries) {
            for (String cheeseColor : cheeseColors ) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        "/images/products/incredible_cheese_" + cheeseColor.toLowerCase() + "_" + cheeseCountry.toLowerCase() + ".png",
                        124.99f,
                        1.0f,
                        2,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Country", cheeseCountry, AttributeType.VARIANT),
                                new ProductAttributeCreateDTO(null, "Color", cheeseColor, AttributeType.VARIANT)
                        )
                );
                cheeses.add((variant));
            }
        }

        List<ProductAttributeCreateDTO> cheeseAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Odor","Cheese smell", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO cheeseBase = new ProductBaseCreateDTO(
                "CH-48+",
                "/images/products/incredible_cheese_yellow.png",
                "Other",
                "Incredible Cheese",
                "The best cheese for sale in a clothing shop.",
                "Mozza",
                cheeses,
                cheeseAttributes
        );
        ProductBaseDTO savedCheeses = productService.createProduct(cheeseBase);


        List<String> phoneModels = List.of("Iphoney 16e","Iphoney 16","Iphoney 16plus");
        List<String> phoneColors = List.of("White", "Gray","Black");

        List<ProductCreateDTO> phones = new ArrayList<>();

        for (String phoneModel : phoneModels) {
            for (String phoneColor : phoneColors ) {
                ProductCreateDTO variant = new ProductCreateDTO(
                        "/images/products/phone_" + phoneColor.toLowerCase() + ".png",
                        249.99f,
                        1.0f,
                        3,
                        List.of(
                                new ProductAttributeCreateDTO(null, "Model", phoneModel, AttributeType.VARIANT),
                                new ProductAttributeCreateDTO(null, "Color", phoneColor, AttributeType.VARIANT)
                        )
                );
                phones.add((variant));
            }
        }

        List<ProductAttributeCreateDTO> phoneAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Type","Phone", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO phoneBase = new ProductBaseCreateDTO(
                "PH-00-",
                "/images/products/phone_black.png",
                "Other",
                "Phone",
                "A phone.",
                "Iphoney",
                phones,
                phoneAttributes
        );
        ProductBaseDTO savedPhones = productService.createProduct(phoneBase);


        List<String> shoeModels = List.of("Left Shoe");
        List<String> shoeColors = List.of("White");
        List<String> shoeConditions = List.of("Brand New", "Slightly Used");

        List<ProductCreateDTO> shoes = new ArrayList<>();

        for (String shoeModel : shoeModels) {
            for (String shoeColor : shoeColors ) {
                for (String shoeCondition : shoeConditions) {
                    ProductCreateDTO variant = new ProductCreateDTO(
                            "/images/products/mike_left_shoe_white.png",
                            shoeCondition.equals("Brand New") ? 999.99f : 799.99f,
                            1.0f,
                            1,
                            List.of(
                                    new ProductAttributeCreateDTO(null, "Model", shoeModel, AttributeType.VARIANT),
                                    new ProductAttributeCreateDTO(null, "Color", shoeColor, AttributeType.VARIANT),
                                    new ProductAttributeCreateDTO(null, "Condition", shoeCondition, AttributeType.VARIANT)
                            )
                    );
                    shoes.add((variant));
                }
            }
        }

        List<ProductAttributeCreateDTO> shoeAttributes = List.of(
                new ProductAttributeCreateDTO(null,"Type","Left shoe", AttributeType.PRODUCT),
                new ProductAttributeCreateDTO(null,"Foot","Left", AttributeType.PRODUCT),
                new ProductAttributeCreateDTO(null,"Compatibility","Does NOT work with right feet", AttributeType.PRODUCT),
                new ProductAttributeCreateDTO(null,"Pairing","Unpaired", AttributeType.PRODUCT)
        );

        ProductBaseCreateDTO shoeBase = new ProductBaseCreateDTO(
                "SH-00-L",
                "/images/products/mike_left_shoe_white.png",
                "Shoes",
                "Mike Air Almost",
                "A single left shoe. Right shoe sold separately (not available).",
                "Mike",
                shoes,
                shoeAttributes
        );
        ProductBaseDTO savedShoes = productService.createProduct(shoeBase);
    }
}
