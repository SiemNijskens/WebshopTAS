import { useState } from "react"
import NavBar from "../NavBar";
import Sidebar from "../Sidebar";
import type { ProductFilters } from "../../types/models";
import { Outlet, useLocation, useNavigate } from "react-router";

const MainLayOut = () => {
    const location = useLocation();
    const navigate = useNavigate();

    const hideSidebar = [
        "/shoppingcart",
        "/checkout",
        "/users"
    ];
    
    const showBurger = !hideSidebar.some(route => location.pathname.startsWith(route));
    const [showOffCanvas, setShowOffCanvas] = useState(false);

    const toggleOffCanvas = () => {
        setShowOffCanvas(!showOffCanvas);
    }

    const [filters, setFilters] = useState<ProductFilters>({
        categories: [],
        // colors: [],
        brands: [],
        sizes: [],
        onSale: false,
    })

    return (
        <>
            <NavBar
                showBurger={showBurger}
                onBurgerClick={toggleOffCanvas}
            />
            {showBurger &&
                <Sidebar
                    filters={filters}
                    setFilters={setFilters}
                    onApply={() => navigate("/products")}
                    isOpen={showOffCanvas}
                    onClose={() => setShowOffCanvas(false)}
                />}
            <Outlet context={{ filters }}/>
        </>
    )
}

export default MainLayOut;