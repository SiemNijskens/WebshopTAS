import NavBar from "../components/NavBar";

const ShoppingcartPage = () => {

    return (
        <>
            <NavBar />
            <>
                cart list
                <li>
                    product name
                    short description
                    product.img
                    amount + price
                    <button>remove from cart</button>
                </li>
            </>
            <>total price in cart</>
            <button>Continue to payment</button>
        </>
    )
}

export default ShoppingcartPage;