import { Outlet, Link } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { CartContext } from "./CartContext";
import { LogInContext } from "./LogInContext";
import './MainPage.css';
import logo from "./img/logo.png";

function MainPage() {

    const {cart, setCart} = useContext(CartContext);
    const {login, setLogin} = useContext(LogInContext);
    const [counter, setCounter] = useState(0);
    const cart_length = cart.length;

    useEffect(() => {
        document.title = "test123 " + cart_length;
    });

    return(
        <>
            <header><img src={logo} alt="website_logo"/></header>
            <nav>Main Menu
                <Link to="/">Home</Link>
                <Link to="/product_list">Products</Link>
                <Link to="/cart">Cart ({cart_length})</Link>
                <Link to="/login">{login.login ? "Log Out" : "Log In"}</Link>
                <Link to="/admin">Admin</Link>
            </nav>
            <Outlet/>
            <footer>Footer</footer>
        </>
    )
}

export default MainPage