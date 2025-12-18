import { API_URL } from "../../App"

export const fetchMe = async () => {
    const response = await fetch(`${API_URL}/users/me`, {
        credentials: "include",
    });
    if (!response.ok) {
        throw new Error("Not authenticated");
    }
    return response.json();
}