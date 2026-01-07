import { Navigate, Outlet } from "react-router";
import { useAuthStore } from "../stores/authStore"
import { useEffect, useState } from "react";

export const AdminRoute = () => {
    const { user } = useAuthStore();
    const [redirect, setRedirect] = useState(false);

    useEffect(() => {
        if (!user || !user.roles.includes("ROLE_ADMIN")) {
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

    if (!user.roles.includes("ROLE_ADMIN")) {
        if (redirect) return <Navigate to="/" replace />;
        return (
            <div style={{ display: "flex", alignItems: "center", gap: "0.5rem" }}>
                <div className="adminloader" />
                <span>You must be admin to access this page. You are being redirected...</span>
            </div>
        )
    }
    return <Outlet />
}