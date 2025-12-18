import { Navigate, Outlet } from "react-router";
import { useAuthStore } from "../stores/authStore"

export const AdminRoute = () => {
    const { user } = useAuthStore();

    if (!user) return <Navigate to="/login" />;
    if (!user.roles.includes("ROLE_ADMIN"))
        return <Navigate to="/" />;

    return <Outlet />
}