/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2025-12-16 13:17:29.

export interface LoginDTO {
    email: string;
    password: string;
}

export interface RegisterDTO {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    zipCode: string;
    houseNumber: string;
    streetName: string;
    city: string;
}

export interface CartItemCreateDTO {
    productId: number;
    amount: number;
}

export interface CartItemDTO {
    id: number;
    product: Product;
    amount: number;
}

export interface CartItemSummaryDTO {
}

export interface CartItemUpdateDTO {
    amount: number;
}

export interface ShoppingCartCreateDTO {
    userId: number;
}

export interface ShoppingCartDTO {
    id: number;
    user: User;
    cartItems: CartItem[];
}

export interface ShoppingCartSummaryDTO {
}

export interface ShoppingCartUpdateDTO {
    id: number;
    cartItems: CartItem[];
}

export interface UserCreateDTO {
    firstName: string;
    lastName: string;
    roles: string[];
    email: string;
    password: string;
    zipCode: string;
    houseNumber: string;
    streetName: string;
    city: string;
}

export interface UserDTO {
    id: number;
    firstName: string;
    lastName: string;
    roles: string[];
    email: string;
    zipCode: string;
    houseNumber: string;
    streetName: string;
    city: string;
}

export interface UserSummaryDTO {
    id: number;
    firstName: string;
    lastName: string;
}

export interface UserUpdateDTO {
    firstName: string;
    lastName: string;
    email: string;
    zipCode: string;
    houseNumber: string;
    streetName: string;
    city: string;
}

export interface Product {
    id: number;
    price: number;
    salePercentage: number;
    stock: number;
    productBase: ProductBase;
    productAttributes: ProductAttribute[];
    product: ProductBase;
}

export interface User {
    id: number;
    firstName: string;
    lastName: string;
    roles: string[];
    email: string;
    zipCode: string;
    houseNumber: string;
    streetName: string;
    city: string;
    shoppingCarts: ShoppingCart[];
}

export interface CartItem {
    id: number;
    product: Product;
    amount: number;
    shoppingCart: ShoppingCart;
}

export interface ProductBase {
    id: number;
    productCode: string;
    name: string;
    description: string;
    productBrand: string;
    productVariants: Product[];
    productAttributes: ProductAttribute[];
}

export interface ProductAttribute {
    id: number;
    attribute: string;
    value: string;
    type: AttributeType;
    productBase: ProductBase;
    product: Product;
}

export interface ShoppingCart {
    id: number;
    user: User;
    cartItemList: CartItem[];
}

export type AttributeType = "PRODUCT" | "VARIANT";
