import { useEffect } from "react";
import { useAuthStore } from "../stores/authStore";
import { getStoredCartId, storeCartId } from "../stores/cartStorage";
import { API_URL } from "../../App";
import { setCart } from "../stores/cartStore";

const useCartLifeCycle = () => {
    const { user } = useAuthStore();

    useEffect(() => {
        const init = async () => {
            const guestCartId = getStoredCartId();

            const url = new URL(`${API_URL}/shoppingcarts/resolve`);
            if (guestCartId) {
                url.searchParams.set("guestCartId", guestCartId.toString());
            }

            const response =  await fetch(url.toString(), {
                method: 'POST',
                credentials: 'include',
            });

            const cart = await response.json();
            setCart(cart);
            storeCartId(cart.id);
        };
        init();
    },[user]);
};

export default useCartLifeCycle;