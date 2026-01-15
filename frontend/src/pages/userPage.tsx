import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { API_URL } from "../App";
import type { MutationError, UserDTO, UserUpdateDTO } from "../types/models";
import { useState } from "react";

const UserPage = () => {
    //const { id } = useParams<{ id : string }>();

    const [formData, setFormData] = useState<UserUpdateDTO>({
        firstName: "firstname",
        lastName: "lastName",
        email: "email",
        zipCode: "zipCode",
        houseNumber: "houseNumber",
        streetName: "streetName",
        city: "city"
    })
    const queryClient = useQueryClient();
    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const [modal, setModal] = useState<boolean>(false);
    const { data, isLoading, error } = useQuery<UserDTO>({
        queryKey: ["user", "me"],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/users/me`, {
                credentials: "include",
            });
            if (!response.ok) {
                throw new Error("Failed to fetch user");
            }
            return response.json();
        },
        //enabled: !!id,
    });
    // console.log(data?.id)

    const updateUser = useMutation<
        void,
        MutationError,
        UserUpdateDTO
    >({
        mutationFn: async (formData: UserUpdateDTO) => {
            const response = await fetch(`${API_URL}/users/${data?.id}`,
                {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(formData)
                });
            if (!response.ok) {
                const errorBody = await response.json();

                throw {
                    message: errorBody.message ?? "Registration failed.",
                    status: response.status,
                } as MutationError;
            }
            return response.json();
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["user"] })
        }, onError: (error) => {
            setErrorMessage(error.message);
        }
    })

    const handleChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
        setErrorMessage(null);
        const { name, value } = e.target;
        setFormData(data => ({ ...data, [name]: value }))
    }

    const handleFormSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        updateUser.mutate({
            ...formData,
            email: formData.email.toLowerCase()
        });
    }

    const modalToggle = () => {
        setModal(!modal);
    }

    const openModal = () => {
        modalToggle()
        if (data !== undefined) {
            setFormData(data)
        }
    }

    if (isLoading) return <p>Loading...</p>
    if (error instanceof Error) return <p>Error: {error.message}</p>

    if (data !== undefined)
        return (
            <>
                {errorMessage && <div style={{ color: "red" }}>{errorMessage}</div>}
                <div className="container" >
                    <div>username: {data.firstName} {data.lastName}</div>
                    <div>address: {data.streetName} {data.houseNumber} {data.city} {data.zipCode} </div>
                    <div>email: {data.email}</div>
                    <button onClick={openModal}>edit profile</button>
                </div>
                <div className="bold">my orders</div>
                {/* (0,-1) in slice om de laatste cart te skippen */}
                {data.shoppingCarts.slice(0, -1).map(cart => (
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
                {modal && (
                    <div className="modal">
                        <div onClick={modalToggle} className="overlay"></div>
                        <div className="modal-content">
                            <form onSubmit={handleFormSubmit}>
                                <fieldset>
                                    <legend>edit profile</legend>
                                    <fieldset>
                                        <legend>name</legend>
                                        <label htmlFor="firstName">First name: </label>
                                        <input
                                            type="text"
                                            placeholder="Your first name"
                                            id="firstName"
                                            name="firstName"
                                            value={formData.firstName}
                                            onChange={handleChange}
                                        /><br />
                                        <label htmlFor="lastName">Last name: </label>
                                        <input
                                            type="text"
                                            placeholder="Your last name"
                                            id="lastName"
                                            name="lastName"
                                            value={formData.lastName}
                                            onChange={handleChange} /><br />
                                    </fieldset>
                                    <fieldset>
                                        <legend>place</legend>
                                        <label htmlFor="streetName">Street name: </label>
                                        <input
                                            type="text"
                                            placeholder="Your streetName"
                                            id="streetName"
                                            name="streetName"
                                            value={formData.streetName}
                                            onChange={handleChange} /><br />
                                        <label htmlFor="houseNumber">House number: </label>
                                        <input
                                            type="text"
                                            placeholder="Your houseNumber"
                                            id="houseNumber"
                                            name="houseNumber"
                                            value={formData.houseNumber}
                                            onChange={handleChange} /><br />
                                        <label htmlFor="city">City: </label>
                                        <input
                                            type="text"
                                            placeholder="Your city"
                                            id="city"
                                            name="city"
                                            value={formData.city}
                                            onChange={handleChange} /><br />
                                        <label htmlFor="zipcode">Zipcode: </label>
                                        <input
                                            type="text"
                                            placeholder="Your zipcode"
                                            id="zipCode"
                                            name="zipCode"
                                            value={formData.zipCode}
                                            onChange={handleChange} /><br />
                                    </fieldset>
                                    <fieldset>
                                        <legend>contact information</legend>
                                        <label htmlFor="email">email: </label>
                                        <input
                                            type="text"
                                            placeholder="Your email"
                                            id="email"
                                            name="email"
                                            value={formData.email}
                                            onChange={handleChange} /><br />
                                    </fieldset>
                                    <button type="submit">submit</button>
                                    <button onClick={modalToggle}> close modal</button>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                )}

            </>
        )
}

export default UserPage;