import { BrowserRouter, Routes, Route } from "react-router";
import LandingPage from "../../pages/landingPage";
import CheckoutPage from "../../pages/checkoutPage";
// import ProductDetailPage from "../../pages/productDetailPage";
import ShoppingcartPage from "../../pages/shoppingcartPage";
import UserPage from "../../pages/userPage";
import AdminPage from "../../pages/adminPage";
import ProductOverviewPage from "../../pages/productOverviewPage";
import LoginForm from "../forms/LoginForm";
import { AdminRoute } from "./adminRoute";
import { AuthRoute } from "./authRoute";
import UserDetailPage from "../../pages/UserDetail";
import NavBar from "../NavBar";
import useCart from "../queries/cartQuery";
import SignUpForm from "../forms/SignUpForm";
import XPage from "../../pages/XPage";
import YPage from "../../pages/YPage";
import MainLayOut from "./MainLayOut";

const Router = () => {
    useCart();

    return(
        <BrowserRouter>
        {/* <NavBar /> */}
            <Routes>
                <Route element={<MainLayOut />}>
                <Route path="/" element={<LandingPage />} />
    
                <Route path="/login" element={<LoginForm />} />
                <Route path="/signup" element={<SignUpForm />} />
                    <Route element={<AdminRoute />}>
                        <Route path="/admin" element={<AdminPage />} />
                        <Route path="/users/:id" element={<UserDetailPage />} />
                    </Route>
                <Route path="/checkout" element={<CheckoutPage />} />
                <Route path="/shoppingcart" element={<ShoppingcartPage />} />
                    <Route element={<AuthRoute />}>
                        <Route path="users/me" element={<UserPage />} />
                    </Route>

                <Route path="/y" element={<YPage />} />
                <Route path="/products" element={<ProductOverviewPage />} />
                <Route path="/products/:productId" element={<XPage />} />
                {/* <Route path="/productDetailPage" element={<ProductDetailPage />} />  */}
                </Route>
            </Routes>
        </BrowserRouter>
    )
}

export default Router;