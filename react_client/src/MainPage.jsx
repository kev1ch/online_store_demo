import { Outlet, Link } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { CartContext } from "./CartContext";
import './MainPage.css';
import logo from "./img/logo.png";

function MainPage() {

    const {cart, setCart} = useContext(CartContext);
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
            </nav>
            <Outlet/>
            <footer>Footer</footer>
        </>
    )
}

export default MainPage