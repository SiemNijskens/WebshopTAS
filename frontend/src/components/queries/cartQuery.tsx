// import { useQuery } from "@tanstack/react-query"
// import type { UserDTO } from "../../types/models"
// import { API_URL } from "../../App"
// import { setCart } from "../stores/cartStore";
// import { useEffect } from "react";
// import { useAuthStore } from "../stores/authStore";

// const useCart = () => {
//     const { user } = useAuthStore();

//     const { data: userWithCart } = useQuery<UserDTO>({
//         queryKey: ["user", user?.id],
//         queryFn: async () => {
//             const response = await fetch(`${API_URL}/users/${user!.id}`);
//             if (!response.ok) {
//                 throw new Error("Failed to fetch shopping cart")
//             }
//             return response.json();
//         },
//         enabled: !!user?.id,
//     });

//     useEffect(() => {
//         const carts = userWithCart?.shoppingCarts;

//         if (carts && carts.length > 0) {
//             setCart(carts[carts.length -1]);
//         }
//     }, [userWithCart]);
// }

// export default useCart;