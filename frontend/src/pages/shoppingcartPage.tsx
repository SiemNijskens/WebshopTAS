import { useCartStore } from "../components/stores/cartStore";

const ShoppingCartPage = () => {
    const { cart } = useCartStore();

    if (!cart || cart.cartItems.length === 0) {
        return <p>Your cart is empty</p>;
    }

    return (
        <div style={{ padding: "2rem" }}>
            <h2>Shopping Cart (debug)</h2>

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
                <table border={1} cellPadding={8} style={{ borderCollapse: "collapse" }}>
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Variant</th>
                            <th>Amount</th>
                            <th>Price/pc</th>
                            {/* <th>Sale %</th> */}
                            <th>Total price</th>
                        </tr>
                    </thead>
                    <tbody>
                        {cart.cartItems.map(item => (
                            <tr key={item.id}>
                                <td>{item.product.name}</td>
                                <td>
                                    {item.product.attributes.map(attr => (
                                        <div key={attr.attribute}>
                                            {attr.attribute} : {attr.value}
                                        </div>
                                    ))}
                                </td>
                                <td>{item.amount}</td>
                                <td>€{(item.product.price * item.product.salePercentage).toFixed(2)} </td>
                                {/* <td>{item.product.salePercentage}</td> */}
                                <td>€{(item.product.price * item.product.salePercentage * item.amount).toFixed(2)}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default ShoppingCartPage;
