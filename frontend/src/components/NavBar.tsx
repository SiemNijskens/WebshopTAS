import { NavLink } from "react-router";
import SidebarLandingPage from "./SidebarLandingPage";

const NavBar = () => {
    
    return (
        <>
        <SidebarLandingPage/>
        <>SVG aanpassen naar 3 stripes</>
            <nav className="navbar">
                <NavLink to="/" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}> Landingpage </NavLink>       
                <NavLink to="/productOverviewPage" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}> ProductOverviewPage </NavLink>
                <NavLink to="/shoppingCartPage" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}> ShoppingcartPage 
                </NavLink>
                <NavLink to="/checkOutPage" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}> CheckoutPage </NavLink>
                <NavLink to="/userPage" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}> UserPage </NavLink>
                <NavLink to="/adminPage" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}> AdminPage </NavLink>
                
            </ nav>
        </>
    )
}

export default NavBar;