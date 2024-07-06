import { Outlet } from "react-router-dom"
import { useContext, useState } from "react"

function MainPage() {
    const [counter, setCounter] = useState(0);
    return(
        <>
            <header>Logo</header>
            <nav>Main Menu</nav>
            <p>Main Page {counter}</p>
            <button onClick={()=>{setCounter(counter+1)}}>Add</button>
            <Outlet/>
            <footer>Footer</footer>
        </>
    )
}

export default MainPage