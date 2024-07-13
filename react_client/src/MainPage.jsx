import { Outlet, Link } from "react-router-dom"
import { useContext, useState } from "react"
import { CartContext } from "./CartContext";
import './MainPage.css'

function MainPage() {

    const {cart, setCart} = useContext(CartContext);
    const [counter, setCounter] = useState(0);
    const cart_length = cart.length;

    return(
        <>
            <header>Logo</header>
            <nav>Main Menu
                <Link to="/">Home</Link>
                <Link to="/product_list">Products</Link>
                <Link to="/cart">Cart ({cart_length})</Link>
            </nav>
            <p>Main Page {counter}</p>
            <button onClick={()=>{setCounter(counter+1)}}>Add</button>
            <Outlet/>
            <footer>Footer</footer>
        </>
    )
}

export default MainPage