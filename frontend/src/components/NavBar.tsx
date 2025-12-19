import { NavLink } from "react-router";
import SidebarLandingPage from "./SidebarLandingPage";
import LoginIfno from "./loginInfo";
import { useAuthStore } from "./stores/authStore";

const NavBar = () => {
    const { user } = useAuthStore();

    return (
        <nav className="navbar">
            <div className="navbar-left">
                <SidebarLandingPage/>
                <>SVG = 3 stripes</>
            </div>
        
            <div className="navbar-center">
                <NavLink to="/" end className="nav-link">Home</NavLink>
                <NavLink to="/products" className="nav-link">Products</NavLink>
                <NavLink to="/shoppingcart" className="nav-link">Shopping Cart </NavLink>
                {/* <NavLink to="/productDetailPage" className="nav-link">ProductDetailPage</NavLink> */}
                <NavLink to="/checkout" className="nav-link">Checkout</NavLink>
                {user && <NavLink to="/users/me" className="nav-link">Profile</NavLink>}
                {user?.roles.includes("ROLE_ADMIN") && <NavLink to="/admin" className="nav-link">Admin</NavLink>}
            </div>

            <div className="navbar-right">
            <LoginIfno />
            </div>
        </nav>
    )
}

export default NavBar;