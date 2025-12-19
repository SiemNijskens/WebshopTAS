import { BrowserRouter, Routes, Route } from "react-router";
import LandingPage from "../../pages/landingPage";
import CheckoutPage from "../../pages/checkoutPage";
import ProductDetailPage from "../../pages/productDetailPage";
import ShoppingcartPage from "../../pages/shoppingcartPage";
import UserPage from "../../pages/userPage";
import AdminPage from "../../pages/adminPage";
import ProductOverviewPage from "../../pages/productOverviewPage";
import LoginForm from "../forms/LoginForm";
import { AdminRoute } from "./adminRoute";
import { AuthRoute } from "./authRoute";
import UserDetailPage from "../../pages/UserDetail";
import NavBar from "../NavBar";
import { SignUpForm } from "../forms/SignUpForm";

const Router = () => {
    return(
        <BrowserRouter>
        <NavBar />
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="/login" element={<LoginForm />} />
                {/* <Route path="/signup" element={<SignUpForm />} /> */}
                    <Route element={<AdminRoute />}>
                        <Route path="/admin" element={<AdminPage />} />
                        <Route path="/users/:id" element={<UserDetailPage />} />
                    </Route>
                <Route path="/checkout" element={<CheckoutPage />} />
                <Route path="/products" element={<ProductOverviewPage />} />
                {/* <Route path="/productDetailPage" element={<ProductDetailPage />} />  */}
                <Route path="/shoppingcart" element={<ShoppingcartPage />} />
                    <Route element={<AuthRoute />}>
                        <Route path="users/me" element={<UserPage />} />
                    </Route>
            </Routes>
        </BrowserRouter>
    )
}

export default Router;