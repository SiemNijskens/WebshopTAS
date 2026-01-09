import { useMutation } from "@tanstack/react-query";
import { useState } from "react";
import { useNavigate } from "react-router";
import type { LoginDTO, MutationError} from "../../types/models";
import { API_URL } from "../../App";
import { login } from "../stores/authStore";

const LoginForm = () => {
  const [email, setEmail] = useState("thomas_webshop@webshop.nl");
  const [password, setPasword] = useState("password123");
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const navigate = useNavigate();

  const loginMutation = useMutation({
      mutationFn: async (userData: LoginDTO) => {
          const response =  await fetch(`${API_URL}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type' : 'application/json'},
            body: JSON.stringify(userData),
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
      onSuccess: (user) => {
        login(user);
        // alert("Hi " + user.firstName + "!");
        navigate(-1);
      },
      onError: (error) => {
        setErrorMessage(error.message);
      }
  });

  const handleFormSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    
    loginMutation.mutate({ email, password });
  }

  return (
    // <div className="login-box">
    //   <h2>Login</h2>
    <div>
    <form className="form" onSubmit={handleFormSubmit}>
      {errorMessage && <div style={{ color: "red" }}>{errorMessage}</div>}
      <div className="form-field">
        <label htmlFor="email">Email: </label>
        <input
          type="text"
          placeholder="Your email"
          id="email"
          name="email"
          value={email}
          onChange={(e) => {
            setErrorMessage(null),
            setEmail( e.target.value )}}
        />
      </div>
      <div className="form-field">
        <label htmlFor="password">Password: </label>
        <input
          type="password"
          placeholder="Your password"
          id="password"
          name="password"
          value={password}
          onChange={(e) => {
            setErrorMessage(null),
            setPasword( e.target.value )}}
        />
        <span className="forgot-password">Forgot password?</span>
      </div>
      <div className="form-actions">
      <button type="submit">Login</button>
      <button type="button" onClick={() => navigate(-1) }>Back</button>
      </div>
    </form>

    <div>
      <button onClick={() => navigate("/signup")}>Sign Up</button>
    </div>
    </div>
  )

}

export default LoginForm;