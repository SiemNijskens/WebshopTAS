import { logout, useAuthStore } from "./stores/authStore";
import { useNavigate } from "react-router";


const LoginIfno = () => {
    const { user } = useAuthStore();
    const navigate = useNavigate();

    if (!user) {
        return (
            <button onClick={() => navigate("/login")}>
                Login
            </button>
        )
    }    

    return (
        <div>
            <span>Hello, {user.firstName}</span>
            <button onClick={logout}>
                Logout
            </button>
        </div>
    );
};

export default LoginIfno;