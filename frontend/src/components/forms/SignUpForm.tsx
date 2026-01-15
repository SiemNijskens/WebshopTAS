import { useMutation } from "@tanstack/react-query";
import type { RegisterDTO, MutationError } from "../../types/models";
import { API_URL } from "../../App";
import { useNavigate } from "react-router";
import { useState } from "react";

const SignUpForm = () => {
    const navigate = useNavigate();

    const [confirmPassword, setConfirmPassword] = useState("password123");
    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const [formData, setFormData] = useState<RegisterDTO>({
        firstName: "Daniel",
        lastName: "Danielsen",
        email: "daniel_danielsen@daniel.nl",
        password: "password123",
        zipCode: "3000ZZ",
        houseNumber: "42",
        streetName: "Eemkloosterstraat",
        city: "Spijkenisse",
    })

    const registerMutiation = useMutation<
    void,
    MutationError,
    RegisterDTO
    >({
        mutationFn: async (registerData: RegisterDTO) => {
            const response = await fetch(`${API_URL}/auth/register`, {
                method: 'POST',
                headers: { 'Content-Type' : 'application/json'},
                body: JSON.stringify(registerData),
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
            alert("Registration completed, you can now log in.")
            navigate("/login");
        },
        onError: (error) => {
            setErrorMessage(error.message);
            console.error("Registration error: ", error);
        },
    });

    const handleChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
        setErrorMessage(null);
        const { name, value } = e.target;
        setFormData(data => ({...data, [name]: value }))
    }

    const handleFormSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (formData.password !== confirmPassword) {
            setErrorMessage("Passwords do not match!");
            return;
        }

        registerMutiation.mutate({
            ...formData,
            email: formData.email.toLowerCase()
        });
    }

    return (
        <div>
        <form className="form" onSubmit={handleFormSubmit}>
            {errorMessage && <div style={{ color: "red" }}>{errorMessage}</div>}
            <div className="form-field">
                <label htmlFor="firstName">First name:</label>
                <input
                    type="text"
                    placeholder="Your first name"
                    id="firstName"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                />
            </div>
            <div className="form-field">
                <label htmlFor="lasttName">Last name:</label>
                <input type="text" placeholder="Your last name" id="lastName" name="lastName" value={formData.lastName} onChange={handleChange} />
            </div>
            <div className="form-field">
                <label htmlFor="email">Email:</label>
                <input type="email" placeholder="Your email" id="email" name="email" value={formData.email} onChange={handleChange} />
            </div>
            <div className="form-field">
                <label htmlFor="password">Password:</label>
                <input type="password" placeholder="Password" id="password" name="password" value={formData.password} onChange={handleChange} />
            </div>
            <div className="form-field">
                <label htmlFor="confirmPassword">Confirm Password:</label>
                <input type="password" placeholder="Confirm password" id="confirmPassword" name="confirmPassword" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
            </div>
            <div className="form-field">
                <label htmlFor="zipCode">Zip Code:</label>
                <input type="text" placeholder="Your Zip Code" id="zipCode" name="zipCode" value={formData.zipCode} onChange={handleChange} />
            </div>
            <div className="form-field">
                <label htmlFor="houseNumber">House Number:</label>
                <input type="text" placeholder="Number" id="houseNumber" name="houseNumber" value={formData.houseNumber} onChange={handleChange} />
            </div>
            <div className="form-field">
                <label htmlFor="streetName">Streetname:</label>
                <input type="text" placeholder="Your Streetname" id="streetName" name="streetName" value={formData.streetName} onChange={handleChange} />
            </div>
            <div className="form-field">
                <label htmlFor="city">City:</label>
                <input type="text" placeholder="Your City" id="city" name="city" value={formData.city} onChange={handleChange} />
            </div>
            <div className="form-actions">
            <button type="submit" disabled={registerMutiation.isPending}>
                {registerMutiation.isPending ? "Signing up..." : "Sign Up"}</button>
            <button type="button" onClick={() => navigate("/")}>Back</button>
            </div>
        </form>
        </div>
    )
}

export default SignUpForm;