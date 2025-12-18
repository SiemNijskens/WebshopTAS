import { BrowserRouter, Routes, Route } from "react-router";
import LandingPage from "./pages/landingPage";
import CheckoutPage from "./pages/checkoutPage";
import ProductDetailPage from "./pages/productDetailPage";
import ShoppingcartPage from "./pages/shoppingcartPage";
import UserPage from "./pages/userPage";
import AdminPage from "./pages/adminPage";
import ProductOverviewPage from "./pages/productOverviewPage";

const Router = () => {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="adminPage" element={<AdminPage />} />
                <Route path="checkOutPage" element={<CheckoutPage />} />
                <Route path="productOverviewPage" element={< ProductOverviewPage/>}/>
                <Route path="shoppingCartPage" element={<ShoppingcartPage />} />
                <Route path="userPage" element={<UserPage />} />
            </Routes>
        </BrowserRouter>
    )
}

export default Router;