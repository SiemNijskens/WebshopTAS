import { API_URL } from "../../App";
import type { LoginDTO } from "../../types/models";


export const login = async (userData: LoginDTO) => {
    const response =  await fetch(`${API_URL}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type' : 'application/json'},
            credentials: "include",
            body: JSON.stringify(userData),
          });
          if (!response.ok) {
            throw new Error("Invalid credentials");
          }
          return response.json();
    };

export const logout = async () => {
    await fetch(`${API_URL}/auth/logout`, {
        method: 'POST',
        credentials: "include"
    });
};