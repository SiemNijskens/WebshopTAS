import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from "./pages/landingPage";
import CheckoutPage from "./pages/checkoutPage";
import ProductDetailPage from "./pages/productDetailPage";
import ShoppingcartPage from "./pages/shoppingcartPage";
import UserPage from "./pages/userPage";


const Router = () => {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="checkOutPage" element={<CheckoutPage />} />
                <Route path="productDetailPage" element={<ProductDetailPage />} /> 
                <Route path="shoppingCart" element={<ShoppingcartPage />} />
                <Route path="user" element={<UserPage />} />
            </Routes>
        </BrowserRouter>
    )
}

export default Router;