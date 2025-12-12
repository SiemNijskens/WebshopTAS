import { NavLink } from "react-router";
import SidebarLandingPage from "./SidebarLandingPage";

const NavBar = () => {
    
    return (
        <>
        <SidebarLandingPage/>
        <>SVG aanpassen naar 3 stripes</>
            <nav className="navbar">
                <NavLink to="/" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>Landingpage</NavLink>
                <NavLink to="/adminPage" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>Admin</NavLink>
                <NavLink to="/shoppingCartPage" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>Shoppingcart    
                </NavLink>
                <NavLink to="/userPage" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>User page </NavLink>
                <NavLink to="/checkOutPage" end className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>User </NavLink>
            </ nav>
        </>
    )
}

export default NavBar;