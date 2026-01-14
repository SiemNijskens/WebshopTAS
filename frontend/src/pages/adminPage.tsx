import NewProductModal from "../components/modals/NewProductModal";


const AdminPage = () => {

    return (
        <>
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
                <br />
                    <NewProductModal />
            </>
        </>
    )
}

export default AdminPage;