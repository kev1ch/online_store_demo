import { Outlet, Link } from "react-router-dom"
import { useContext, useState } from "react"
import './MainPage.css'

function MainPage() {
    const [counter, setCounter] = useState(0);
    return(
        <>
            <header>Logo</header>
            <nav>Main Menu
                <Link to="/">Home</Link>
                <Link to="/product_list">Products</Link>
                <Link to="/cart">Cart</Link>
            </nav>
            <p>Main Page {counter}</p>
            <button onClick={()=>{setCounter(counter+1)}}>Add</button>
            <Outlet/>
            <footer>Footer</footer>
        </>
    )
}

export default MainPage