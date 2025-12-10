import NavBar from "../components/NavBar";

const UserPage = () => {

    return (
        <>
            <NavBar />
            <>
                username
                address
                email
                paymentmethod
                customerId
                <button> edit user</button>
            </>
            <>
                my orders
                <li>
                    product name
                    short description
                    product.img
                    price
                    delivered or not?
                </li>
            </>
        </>
    )
}

export default UserPage;