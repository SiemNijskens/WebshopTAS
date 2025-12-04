const EditProductModal = () => {

    return (
        <>
        <form>
            <label>productName</label>
            <input type="text"/>
            <label>product description</label>
            <input type="text"/>
            map alle properties van een product
            <li>
                <label>property</label>
                <input type="text"/>
            </li>
            <button type="submit">confirm edit</button>
        </form>
        <button>delete product</button>
        <button>cancel</button>
        </>
    )
}

export default EditProductModal;