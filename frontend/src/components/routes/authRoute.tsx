import { Navigate, Outlet } from "react-router";
import { useAuthStore } from "../stores/authStore"
import { useEffect, useState } from "react";

export const AuthRoute = () => {
    const { user } = useAuthStore();
    const [redirect, setRedirect] = useState(false);

    useEffect(() => {
        if (!user) {
            const timer = setTimeout(() => setRedirect(true), 3000);
            return () => clearTimeout(timer);
        }
    }, [user]);

    if (!user) {
        if (redirect) return <Navigate to="/login" replace />;
        return (
            <div style={{ display: "flex", alignItems: "center", gap: "0.5rem" }}>
                <div className="loader" />
                <span>You must be logged in to access this page. You are being redirected...</span>
            </div>
        )
    }
    return <Outlet />;
};