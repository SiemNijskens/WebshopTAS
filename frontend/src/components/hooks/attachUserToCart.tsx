import { API_URL } from "../../App";
import { setCart } from "../stores/cartStore";

const attachUserToCart = async (userId: number) => {
    const cartId = localStorage.getItem("cartId");
    console.log("[ATTACH USER] userId=", userId, "cartId=", cartId);
    
    if (!cartId) return;

    const response =  await fetch(`${API_URL}/shoppingcarts/${cartId}/attach-user/${userId}`, {
        method: 'POST',
        credentials: 'include',
    });

    console.log("[ATTACH USER] response status=", response.status);
    const text = await response.text();
    console.log("[ATTACH USER] raw text=", text);

    if (text) {
        const cart = JSON.parse(text);
        console.log("[ATTACH USER] parsed cart=", cart);
        setCart(cart);
    }
};

export default attachUserToCart;