import { useEffect, useRef, useState } from "react"
import NavBar from "../NavBar";
import Sidebar from "../Sidebar";
import type { ProductFilters } from "../../types/models";
import { Outlet, useLocation, useNavigate } from "react-router";
import { clearCart, createCart, fetchCart } from "../stores/cartStore";
import { useAuthStore } from "../stores/authStore";
// import useAttachUserToCart from "../hooks/attachOrMergeCart";

const MainLayOut = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { user} = useAuthStore();

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

    // useInitializedCart();
    // useCartLifeCycle();

    const initialized = useRef(false);

    // useEffect(() => {
    //     if (initialized.current) return;
    //     initialized.current = true

    //     const initCart = async () => {
    //         const cartId = localStorage.getItem("cartId");

    //         if (cartId) {
    //             try {
    //                 await fetchCart(Number(cartId));
    //             } catch {
    //                 await createCart();
    //             }
    //         } else {
    //             await createCart();
    //         }
    //     };
    //     initCart();
    // }, []);

    useEffect(() => {
        if (initialized.current) return;
        initialized.current = true;

    // Only create a cart if:
    // - user is NOT logged in
    // - no cartId exists yet
        if (!user && !localStorage.getItem("cartId")) {
            console.log("[INIT CART] creating guest cart");
            createCart();
        }
    }, [user]);

    useEffect(() => {
        const cartId = localStorage.getItem("cartId");
        if (!cartId) return;

        console.log("[INIT CART] fetching cart", cartId);

        fetchCart(Number(cartId)).catch(() => {
            console.warn("[INIT CART] cart not found, clearing");
            localStorage.removeItem("cartId");
        clearCart();
        });
    }, []);

    const handleApplyFilters = () => {
        navigate("/products");
        setShowOffCanvas(false);
    }

    // useAttachUserToCart();

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
                    onApply={handleApplyFilters}
                    isOpen={showOffCanvas}
                    onClose={() => setShowOffCanvas(false)}
                />}
            <Outlet context={{ filters }}/>
        </>
    )
}

export default MainLayOut;