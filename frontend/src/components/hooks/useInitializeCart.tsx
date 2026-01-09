import { useMutation } from "@tanstack/react-query";
import { setCart } from "../stores/cartStore"
import { API_URL } from "../../App";
import { storeCartId } from "../stores/cartStorage";
import { useEffect } from "react";

const useInitializedCart = () => {

    const createCartMutation = useMutation({
        mutationFn: async () => {
            const response = await fetch(`${API_URL}/shoppingcarts`, {
                method: 'POST'
            });
            if (!response.ok) {
                throw new Error("Failed to create a cart");
            }
            return response.json();
        },
        onSuccess: (newCart) => {
            setCart(newCart);
            storeCartId(newCart.id);
        },
    });

    useEffect(() => {
        const initilizeCart = async () => {
            const cartId = localStorage.getItem("cardId");

            if (!cartId) {
                createCartMutation.mutate();
                return;
            }

            try {
                const response =  await fetch(`${API_URL}/shoppingcarts/${cartId}`);
                if (!response.ok) {
                    throw new Error("Cart not found");
                }

                const existingCart = await response.json();
                setCart(existingCart);
            } catch (error) {
                createCartMutation.mutate();
            }
        };
        initilizeCart();

    }, []);
}

export default useInitializedCart;