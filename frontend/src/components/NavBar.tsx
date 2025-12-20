import { NavLink } from "react-router";
import SidebarLandingPage from "./SidebarLandingPage";
import LoginIfno from "./loginInfo";
import { useAuthStore } from "./stores/authStore";
import { useCartStore } from "./stores/cartStore";

const NavBar = () => {
    const { user } = useAuthStore();
    const { cart } = useCartStore();
    const isCartEmpty = !cart || cart.cartItems.length === 0;
    
    return (
        <nav className="navbar">
            <div className="navbar-left">
                <SidebarLandingPage/>
                {/* <>SVG = 3 stripes</> */}
                <img src="pngtree-a-krabby-parry-png-image_13066983.PNG" width="50" height="50"/>
            </div>
        
            <div className="navbar-center">
                <NavLink to="/" end className="nav-link">Home</NavLink>
                <NavLink to="/products" className="nav-link">Products</NavLink>
                <NavLink to="/shoppingcart" className={`nav-link ${isCartEmpty ? "disabled" : ""}`}>Shopping Cart </NavLink>
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