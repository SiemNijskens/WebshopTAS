import { setCart } from "../stores/cartStore";
import { API_URL } from "../../App";

// const attachOrMergeCart = async () => {
//   const guestCartId = localStorage.getItem("cartId");

//   if (!guestCartId) return;

//   await fetch(`${API_URL}/shoppingcarts/merge/${guestCartId}`, {
//     method: "POST",
//     credentials: "include",
//   });

//   const response = await fetch(`${API_URL}/users/me/shoppingcart`, {
//     credentials: "include",
//   });

//   const cart = await response.json();
//   setCart(cart);
//   localStorage.setItem("cartId", cart.id);
// };

// export default attachOrMergeCart;

const attachOrMergeCart = async (userId: number) => {
    const guestCartId = localStorage.getItem("cartId");

    console.log("[ATTACH OR MERGE] userId:", userId, "guestCartId:", guestCartId);

    const response = await fetch(`${API_URL}/users/me/shoppingcart`, { credentials: 'include' });
    if (!response.ok) return;

    const userCart = await response.json();

    if (guestCartId && userCart?.id && userCart.id !== Number(guestCartId)) {
        const mergeResponse = await fetch(`${API_URL}/shoppingcarts/merge/${guestCartId}`, {
            method: 'POST',
            credentials: 'include'
        });
        const mergedCart = await mergeResponse.json();
        setCart(mergedCart);
    } else if (guestCartId && !userCart?.id) {
        const attachResponse = await fetch(`${API_URL}/shoppingcarts/${guestCartId}/attach-user/${userId}`, {
            method: 'POST',
            credentials: 'include'
        });
        const attachedCart = await attachResponse.json();
        setCart(attachedCart);
    } else if (userCart?.id) {
        setCart(userCart);
    }
};

export default attachOrMergeCart;