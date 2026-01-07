import { createStore } from "@odemian/react-store";
import type { ShoppingCartDTO } from "../../types/models";

interface CartState {
    cart: ShoppingCartDTO | null;
}

const initialCartState: CartState = {
    cart: null,
}

export const [useCartStore, setCartStore] = createStore<CartState>(initialCartState);

export const setCart = (cart: ShoppingCartDTO): void => {
    setCartStore({ cart});
}

export const clearCart = () => {
    setCartStore({ cart: null })
}