import { createStore } from "@odemian/react-store";
import type { ShoppingCartDTO } from "../../types/models";
import { API_URL } from "../../App";

interface CartState {
    cart: ShoppingCartDTO | null;
    cartId: number | null;
}

const initialCartState: CartState = {
    cart: null,
    cartId: null,
}

export const [useCartStore, setCartStore] = createStore<CartState>(initialCartState);

export const setCart = (cart: ShoppingCartDTO): void => {
    setCartStore({
        cart,
        cartId: cart.id });
}

export const clearCart = () => {
    setCartStore({
        cart: null,
        cartId: null })
}

export const fetchCart = async (cartId: number) => {
    console.log("[FETCH CART] cardId=", cartId);
    const response = await fetch(`${API_URL}/shoppingcarts/${cartId}`);
    console.log("[FETCH CART] status=", response.status);
    if (!response.ok) {
        console.warn("[FETCH CART] invalid cartId, recreating");
        localStorage.removeItem("cartId");
        clearCart();
        await createCart();
        // throw new Error("Cart not found");
        return;
    }
    const text = await response.text();
    console.log("[FETCH CART] raw text=", text);
    const cart = JSON.parse(text);
    console.log("[FETCH CART] parsed cart=", cart);
    // const cart = await response.json();
    setCartStore({ cart, cartId: cart.id });
    return cart;
}

export const createCart = async () => {
    console.log("[CREATE CART] called");
    const response = await fetch(`${API_URL}/shoppingcarts`, {
        method: 'POST' });
    console.log("[CREATE CART] status=", response.status);
    if (!response.ok) throw new Error("Failed to create cart");

    const text = await response.text();
    console.log("[CREATE CART] raw text=", text);

    const cart = JSON.parse(text);
    // const cart = await response.json();
    console.log("[CREATE CART] created cart=", cart);
    localStorage.setItem("cartId", cart.id.toString());
    setCartStore({ cart, cartId: cart.id });
    return cart;
}