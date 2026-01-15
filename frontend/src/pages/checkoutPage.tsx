import { useQuery } from "@tanstack/react-query";
import { useCartStore } from "../components/stores/cartStore";
import type { UserDTO } from "../types/models";
import { API_URL } from "../App";
import { useNavigate } from "react-router";
import { useState } from "react";

const CheckoutPage = () => {
    const { cart } = useCartStore();
    const navigate = useNavigate();
    const [selectedPaymentMethod, setSelectedPaymentMethod] = useState("");
    const [selectedShipping, setSelectedShipping] = useState("");
    const [shipping, setShipping] = useState(0);
    // let shipping = 0;
    const finalPrice = cart!.totalPrice + shipping;

    const { data, isLoading, error} = useQuery<UserDTO>({
        queryKey: ['user', 'me'],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/users/me`, {
            credentials: 'include',
        });
        if (!response.ok) {
            throw new Error("User not found");
        }
        return response.json();
        },
    });

    if (!cart) return <p>Cart not found</p>;

    if (isLoading) return <p>Loading user...</p>;

    if (error) return <p>Error!</p>;

    if (!data) return  <p>No data found!</p>;

    const handlePaymentMethod = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedPaymentMethod(e.target.value);
    }

    const handleShipping = (e: React.ChangeEvent<HTMLSelectElement>) => {
        e.preventDefault();
        setSelectedShipping(e.target.value);
        if (selectedShipping === "PostNL") setShipping(4.95);
        if (selectedShipping === "DHL") setShipping(5.95);
        if (selectedShipping === "UPS") setShipping(6.95);

    }

    return (
        <div>
            <h2>Checkout</h2>
            <div style={{ display: "flex", justifyContent: "flex-end" }}>
            <button onClick={() => navigate("/shoppingcarts")}>Back</button>
            </div>
            <hr />
            <div style={{ display: "grid", gridTemplateColumns: "3fr 1fr" }}>
                <div>
                    <h3>Billing address</h3>
                    <span>{data.firstName} {data.lastName}</span>
                    <div>{data.streetName} {data.houseNumber}</div>
                    <span>{data.zipCode}</span>
                    <div>{data.city}</div>
                    <hr />
                    <h3>Payment method</h3>
                    <select
                        value={selectedPaymentMethod}
                        onChange={handlePaymentMethod}
                    >
                        <option disabled value="">-- Select Payment method --</option>
                        <option value="iDeal">iDeal</option>
                        <option value="Credit Card">Credit Card</option>
                        <option value="Afterpay">Afterpay</option>
                    </select>
                    <hr />
                    <select
                        value={selectedShipping}
                        onChange={handleShipping}
                    >
                        <option disabled value="">-- Select Sender --</option>
                        <option value="PostNL">PostNL</option>
                        <option value="DHL">DHL</option>
                        <option value="UPS">UPS</option>
                    </select>
                </div>
                <div className="summary">
                    <h2>Total price</h2>
                    <hr />
                    <p>Subtotal: €{cart?.totalPrice.toFixed(2)}</p>
                    <p>Shipping: €{shipping}</p>
                    <p>Total: €{finalPrice.toFixed(2)}</p>
                </div>
            </div>
        </div>
    )
}

export default CheckoutPage;