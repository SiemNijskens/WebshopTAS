import NavBar from "../components/NavBar";

const AdminPage = () => {

    return (
        <>
            <NavBar />
            <>
                <li>
                    username
                    email
                    customerId
                    <button>delete user</button>
                </li>
            </>
            <>
                <>
                    searchproduct
                    product list
                    <button>add new product</button>
                </>
                <li>
                    productname
                    price
                    stock
                    <button>edit product</button>
                </li>
            </>
        </>
    )
}

export default AdminPage;