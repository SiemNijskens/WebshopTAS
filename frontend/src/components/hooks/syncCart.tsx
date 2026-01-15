import { API_URL } from "../../App";
import type { UserSummaryDTO } from "../../types/models";
import { clearCart, createCart, fetchCart, setCart } from "../stores/cartStore";

const syncCart = async (user: UserSummaryDTO | null) => {

    const cartId = localStorage.getItem("cartId");

    if (user) {
        console.log("[SYNC CART] user logged in, fetching my Cart");

        const response = await fetch(`${API_URL}/users/me/shoppingcart`, {
            credentials: 'include',
        });

        if (!response.ok) return;

        const userCart = await response.json();

        if (userCart) {
            setCart(userCart);
            localStorage.setItem("CartId", userCart.id);
        }
        return;
    }

    if (cartId) {
        console.log("[SYNC CART] guest cartId found", cartId);
        try {
            await fetchCart(Number(cartId));
            return;
        } catch {
            console.warn("[SYNCH CART] guest cart invalid, clearing");
            localStorage.removeItem("cartId");
            clearCart();
        }
    }

    console.log("[SYNC CART] creating guest cart");
    await createCart();
};

export default syncCart;