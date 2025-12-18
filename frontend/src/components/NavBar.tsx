import { NavLink } from "react-router";
import SidebarLandingPage from "./SidebarLandingPage";
import LoginIfno from "./loginInfo";
import { useAuthStore } from "./stores/authStore";

const NavBar = () => {
    const { user } = useAuthStore();

    return (
        <>
        <SidebarLandingPage/>
        <>SVG aanpassen naar 3 stripes</>
            <nav className="navbar">
                <NavLink to="/" end className="nav-link">Home </NavLink>
                {user && <NavLink to="/users/me" className="nav-link">Profile </NavLink>}
                {user?.roles.includes("ROLE_ADMIN") && <NavLink to="/admin" className="nav-link">Admin </NavLink>}
                <NavLink to="/shoppingCartPage" className="nav-link">Shopping Cart </NavLink>
                <NavLink to="/productDetailPage" className="nav-link">ProductDetailPage </NavLink>
                <NavLink to="/checkout" className="nav-link">Checkout </NavLink>
            </ nav>
            <LoginIfno />
        </>
    )
}

export default NavBar;