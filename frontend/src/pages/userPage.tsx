import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../App";
import type { UserDTO } from "../types/models";

const UserPage = () => {
    //const { id } = useParams<{ id : string }>();

    const { data, isLoading, error } = useQuery<UserDTO>({
        queryKey: ["user"],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/users/me`, {
                //credentials: "include",
            });
            if (!response.ok) {
                throw new Error("Failed to fetch user");
            }
            return response.json();
        },
        //enabled: !!id,
    });
    // console.log(data?.id)

    if (isLoading) return <p>Loading...</p>
    if (error instanceof Error) return <p>Error: {error.message}</p>

    if (data !== undefined)
        return (
            <>
                <div className="container" >
                    <div>username: {data.firstName} {data.lastName}</div>
                    <div>address:
                        {data.streetName}
                        {data.houseNumber}
                        {data.city}
                        {data.zipCode} </div>
                    <div>email: {data.email}</div>
                    <button>edit profile</button>
                </div>
                my orders
                {/* (0,-1) in slice om de laatste cart te skippen */}
                {data.shoppingCarts.slice().map(cart => (
                    <div key={cart.id} className="card">
                        {cart.cartItems.map(item => (
                            <div key={item.id} >
                                <p><span className="bold">name: </span>{item.product.name}</p>
                                <p><span className="bold">description: </span>{item.product.description}</p>
                                <img width={"50%"} src={item.product.imageURL} />
                                <p><span className="bold">price: </span>€{item.product.price}</p>
                                <p><span className="bold">amount: </span> {item.amount}</p>
                                {item.product.attributes.map(attribute => (
                                    <>
                                        {attribute.attribute}
                                        {attribute.value}
                                    </>
                                ))}
                            </div>
                        ))}
                    </div>
                ))}

                <form>
                    <fieldset>
                        <legend>edit profile</legend>
                        <fieldset>
                            <legend>name</legend>
                            <label htmlFor="firstName">First name: </label>
                            <input type="text" id="firstName" name="firstName" /><br />
                            <label htmlFor="lastName">Last name: </label>
                            <input type="text" id="lastName" name="lastName" /><br />
                        </fieldset>
                        <fieldset>
                            <legend>place</legend>
                            <label htmlFor="streetName">Street name: </label>
                            <input type="text" id="streetName" name="streetName" /><br />
                            <label htmlFor="houseNumber">House number: </label>
                            <input type="text" id="houseNumber" name="houseNumber" /><br />
                            <label htmlFor="city">City: </label>
                            <input type="text" id="city" name="city" /><br />
                            <label htmlFor="zipcode">Zipcode: </label>
                            <input type="text" id="zipcode" name="zipcode" /><br />
                        </fieldset>
                        <fieldset>
                            <legend>contact information</legend>
                            <label htmlFor="email">email: </label>
                            <input type="text" id="email" name="email" /><br />
                        </fieldset>
                    </fieldset>
                </form>
            </>
        )
}

export default UserPage;