import { createStore } from "@odemian/react-store";
import type { UserSummaryDTO } from "../../types/models";
import { clearCart, createCart } from "./cartStore";
import attachOrMergeCart from "../hooks/attachOrMergeCart";

interface AuthState {
    user: UserSummaryDTO | null,
}

const LOCAL_KEY = "authUser";

const savedUser = localStorage.getItem(LOCAL_KEY);

const initialAuthState: AuthState = {
    user: savedUser ? JSON.parse(savedUser) : null,
};

export const [useAuthStore, setAuthStore] = createStore<AuthState>(initialAuthState);

export const login = async (user: UserSummaryDTO) => {
    localStorage.setItem(LOCAL_KEY, JSON.stringify(user));
    setAuthStore({ user });
    console.log("[LOGIN] calling attachOrMergeCart");
    await attachOrMergeCart(user.id);
    console.log("[LOGIN] attachOrMergeCart finished");
};

export const logout = () => {
    localStorage.removeItem(LOCAL_KEY);
    localStorage.removeItem("user");
    localStorage.removeItem("cardId");
    clearCart();
    createCart();
    setAuthStore({ user: null })
}