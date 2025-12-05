import NavBar from "../components/NavBar";

const CheckoutPage = () => {

    return (
        <>
            <NavBar />
            <>
                <li>
                    product name
                    amount & price
                    <button>remove</button>
                </li>
                total price
            </>
            <form>
                <>payment method dropdown</>
                <>billing info</>
                <>name:</>
                <input type="text" />
                <>address + number:</>
                <input type="text" />
                <>zipcode:</>
                <input type="text" />
                <>deliverytime</>
                <input type="text" />
                <button type="submit">pay</button>
            </form>
        </>
    )
}

export default CheckoutPage;