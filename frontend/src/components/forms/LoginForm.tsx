import { useMutation } from "@tanstack/react-query";
import { useState } from "react";
import { useNavigate } from "react-router";
import type { LoginDTO } from "../../types/models";
import { API_URL } from "../../App";
import { login } from "../stores/authStore";

const LoginForm = () => {
  const [email, setEmail] = useState("thomas_webshop@webshop.nl");
  const [password, setPasword] = useState("password123");
  const navigate = useNavigate();

  const loginMutation = useMutation({
      mutationFn: async (userData: LoginDTO) => {
          const response =  await fetch(`${API_URL}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type' : 'application/json'},
            body: JSON.stringify(userData),
          });
          if (!response.ok) {
            throw new Error("Failed to fetch user");
          }
          return response.json();
      },
      onSuccess: (user) => {
        login(user);
        alert("Hi " + user.firstName + "!");
        navigate(-1);
      },
  });

  const handleFormSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    
    loginMutation.mutate({ email, password });
  }

  return (
    <form onSubmit={handleFormSubmit}>
      <div>
        <label htmlFor="email">Email: </label>
        <input
          type="text"
          placeholder="Your email"
          id="email"
          name="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>
      <div>
        <label htmlFor="password">Password: </label>
        <input
          type="password"
          placeholder="Your password"
          id="password"
          name="password"
          value={password}
          onChange={(e) => setPasword(e.target.value)}
        />
      </div>
      <button type="submit">Login</button>
      <button type="button" onClick={() => navigate(-1) }>Back</button>
      <span>
        Forgot password?
      </span>
    </form>
  )

}

export default LoginForm;