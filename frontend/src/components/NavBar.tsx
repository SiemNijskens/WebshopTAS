import { NavLink } from "react-router";
import LoginIfno from "./loginInfo";
import { useAuthStore } from "./stores/authStore";
import { useCartStore } from "./stores/cartStore";

interface BurgerProps {
    showBurger: boolean;
    onBurgerClick: () => void;
}

const NavBar = ( { showBurger, onBurgerClick }: BurgerProps) => {
    const { user } = useAuthStore();
    const { cart } = useCartStore();
    const totalItems = cart?.cartItems.reduce((total, item) => total + item.amount, 0) ?? 0;
    // const isCartEmpty = !cart?.cartItems || cart?.cartItems.length === 0;

    return (
        <nav className="navbar">
            <div className="navbar-left">
                {showBurger && <img src="images/other/burger-menu.png" width="50" height="50" onClick={onBurgerClick} style={{ cursor: "pointer" }}/>}
            </div>
        
            <div className="navbar-center">
                <NavLink to="/" end className="nav-link">Home</NavLink>
                <NavLink to="/products" className="nav-link">Products</NavLink>
                {/* <NavLink to="/shoppingcarts" className={`nav-link ${isCartEmpty ? "disabled" : ""}`}>Shopping Cart </NavLink> */}
                {/* <NavLink to="/shoppingcarts" className="nav-link">Shopping Cart</NavLink> */}
                {/* <NavLink to="/shoppingcarts"><img src="public\images\products\shopping-cart.png" className="nav-link" width="30" height="30"/></NavLink> */}
                {/* <NavLink to="/productDetailPage" className="nav-link">ProductDetailPage</NavLink> */}
                {/* <NavLink to="/checkout" className="nav-link">Checkout</NavLink> */}
                {user && <NavLink to="/users/me" className="nav-link">Profile</NavLink>}
                {user?.roles.includes("ROLE_ADMIN") && <NavLink to="/admin" className="nav-link">Admin</NavLink>}
            </div>

            <div className="navbar-right">
            <LoginIfno />
                <div className="container-cart">
                    <NavLink to="/shoppingcarts">
                        <img
                            src="images/other/shopping-cart.png"
                            className="nav-link"
                            width="30"
                            height="30"
                        />
                        { totalItems > 0 && <div className="cart-circle">
                            <div className="cart-circle-amount">
                            {totalItems}
                            </div>
                            </div>
                        }
                    </NavLink>
                </div>
            </div>
        </nav>
    )
}

export default NavBar;