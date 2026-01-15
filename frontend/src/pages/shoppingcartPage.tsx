import { useMutation } from "@tanstack/react-query";
import { API_URL } from "../App";
import { setCart, useCartStore } from "../components/stores/cartStore";
import '../styles/shoppingcart.css';
import { useNavigate } from "react-router";
import { useAuthStore } from "../components/stores/authStore";

const ShoppingCartPage = () => {
    const { cart } = useCartStore();
    const { user } = useAuthStore();
    const navigate = useNavigate();

    const updateItemMutation = useMutation({
        mutationFn: async ({
            cartItemId,
            delta,
        }: {
            cartItemId: number,
            delta: number,
        }) => {
            const response = await fetch(`${API_URL}/cartitems/${cartItemId}`, {
                method: 'PATCH',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ quantity: delta }),
                credentials: 'include',
                }
            );
            if (!response.ok) {
                throw new Error("Failed to update cart item");
            }
            return response.json();
        },
        onSuccess: (updatedCart) => {
            setCart(updatedCart);
        },
    });

    const removeItemMutation = useMutation({
        mutationFn: async ({
            cartItemId,
        }: {
            cartItemId: number,
        }) => {
            const response = await fetch(`${API_URL}/cartitems/${cartItemId}`, {
                method: 'DELETE',
                credentials: 'include',
                }
            );
            if (!response.ok) {
                throw new Error("Failed to remove cart item from shopping cart")
            }
            return response.json();
        },
        onSuccess: (updatedCart) => {
            setCart(updatedCart);
        },
    });

    if (!cart || cart.cartItems.length === 0) {
        return <p>Your cart is empty</p>;
    }

    return (
        <div style={{ padding: "2rem" }}>
            <h2>Shopping Cart</h2>

            <p><b>Cart ID:</b> {cart.id}</p>

            {cart.user ? (
                <p><b>User:</b> {cart.user.firstName} (id: {cart.user.id})</p>
            ) : (
                <p><b>User:</b> none (guest cart)</p>
            )}

            <hr />

            {cart.cartItems.length === 0 ? (
                <p>Cart is empty</p>
            ) : (
                <div style={{ display: "grid", gridTemplateColumns: "3fr 1fr" }}>
                <table>
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Variant</th>
                            <th>Amount</th>
                            <th>Price/pc</th>
                            <th>Subtotal</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {cart.cartItems.map(item => (
                            <tr key={item.id}>
                                <td>
                                    <div style={{ display: "grid", gridTemplateColumns: "1fr auto", alignItems: "center" }}>
                                        {<img src={item.product.imageURL} width={100} height={100} />}
                                        <div>
                                            {item.product.name}
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    {item.product.attributes.map(attr => (
                                        <div key={attr.attribute}>
                                            {attr.attribute} : {attr.value}
                                        </div>
                                    ))}
                                </td>
                                <td>
                                    <div style={{ display: "grid", gridTemplateColumns: "1fr auto", alignItems: "center", gap: "0.75rem" }}>
                                        <div style={{ textAlign: "center" }}>
                                            {item.amount}
                                        </div>
                                        <div style={{ display: "flex", flexDirection: "column" }}>
                                            <button 
                                                disabled={updateItemMutation.isPending || item.product.stock === item.amount}
                                                onClick={() => updateItemMutation.mutate({ cartItemId: item.id, delta: +1 })}>+</button>
                                            <button
                                                disabled={updateItemMutation.isPending}
                                                onClick={() => updateItemMutation.mutate({ cartItemId: item.id, delta: -1 })}>-</button>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <span className={`price ${item.product && item.product.salePercentage < 1 ? "old-price" : ""}`}>
                                        €{item.product.price}
                                    </span>
                                    {/* <span style={{ display: "inline-block", textIndent: 5 }}> */}
                                    <span>
                                        {item.product && item.product.salePercentage < 1 && <div className="sale-price">
                                        €{(item.product.price * item.product.salePercentage).toFixed(2)}</div>}
                                    </span>
                                </td>
                                <td>€{(item.product.price * item.product.salePercentage * item.amount).toFixed(2)}</td>
                                <td>
                                    <div style={{ cursor: "pointer" }} onClick={() => removeItemMutation.mutate({ cartItemId: item.id })}>
                                        {<img src="images/other/trash-can.png" width={20} height={20} />}
                                    </div>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                    <div className="summary">
                        <h2>Order Summary</h2>
                        <hr />
                        <b>Total:</b> €{cart.totalPrice.toFixed(2)}
                        <hr />
                        <button onClick={() => {
                            if (!user) {
                                navigate("/login");
                            } else {
                                navigate("/checkout");
                            }
                        }}
                            className="checkout" >
                            Checkout
                        </button>
                        {!user && <p>Please log in to proceed to checkout.</p>}
                    </div>
                </div>
            )}
        </div>
    );
};

export default ShoppingCartPage;
