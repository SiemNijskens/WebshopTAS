import { useEffect, useRef, useState } from "react"
import NavBar from "../NavBar";
import Sidebar from "../Sidebar";
import type { ProductFilters } from "../../types/models";
import { Outlet, useLocation, useNavigate } from "react-router";
import { clearCart, createCart, fetchCart } from "../stores/cartStore";
import { useAuthStore } from "../stores/authStore";
import syncCart from "../hooks/syncCart";
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

    const initialized = useRef(false);

    useEffect(() => {
        if (initialized.current) return;
        initialized.current = true;

        const run = async () => {
            console.log("[CART INIT] start");

            // 1️⃣ Logged-in user → backend owns cart
            if (user) {
                console.log("[CART INIT] user logged in → fetch user cart");
                await syncCart(user);
                return;
            }

            // 2️⃣ Guest user
            const cartId = localStorage.getItem("cartId");

            if (cartId) {
                console.log("[CART INIT] guest cartId found", cartId);
                try {
                    await fetchCart(Number(cartId));
                    return;
                } catch {
                    console.warn("[CART INIT] invalid guest cart → clearing");
                    localStorage.removeItem("cartId");
                    clearCart();
                }
            }

            // 3️⃣ No cart → create guest cart
            console.log("[CART INIT] creating guest cart");
            await createCart();
        };

        run();
    }, [user]);

    // const initialized = useRef(false);

    // useEffect(() => {
    //     if (initialized.current) return;
    //     initialized.current = true;

    // // Only create a cart if:
    // // - user is NOT logged in
    // // - no cartId exists yet
    //     if (!user && !localStorage.getItem("cartId")) {
    //         console.log("[INIT CART] creating guest cart");
    //         createCart();
    //     }
    // }, [user]);

    // useEffect(() => {
    //     const cartId = localStorage.getItem("cartId");
    //     if (!cartId) return;

    //     console.log("[INIT CART] fetching cart", cartId);

    //     fetchCart(Number(cartId)).catch(() => {
    //         console.warn("[INIT CART] cart not found, clearing");
    //         localStorage.removeItem("cartId");
    //     clearCart();
    //     });
    // }, []);

    // useEffect(() => {
    // if (initialized.current) return;
    // initialized.current = true;

    //     syncCart(user);
    // },[user]);

    // useEffect(() => {
    //     const onFocus = () => {
    //         console.log("[SYNC CART] window focus");
    //         syncCart(user);
    //     };

    //     window.addEventListener("focus", onFocus);
    //     return () => window.addEventListener("focus", onFocus);
    // },[user]);

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