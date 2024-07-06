import './App.css'
import Component1 from './Component1'
import Component2 from './Component2'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import MainPage from './MainPage'
import ProductList from './ProductList'
import Cart from './Cart'

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage/>}>
          <Route path="/test1" element={<Component1/>}/>
          <Route path="/test2" element={<Component2/>}/>
          <Route path="/cart" element={<Cart/>}/>
          <Route path="/product_list" element={<ProductList/>}/>
          <Route path="/" element={<ProductList/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
