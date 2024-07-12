import './App.css'
import Component1 from './Component1'
import Component2 from './Component2'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import MainPage from './MainPage'
import ProductList from './ProductList'
import Cart from './Cart'
import product_list from './product_list'
import { useState } from 'react'
import { ProductsContext } from './ProductsContext'
import { ProductContext } from './ProductContext'
import ProductPage from './ProductPage'

function App() {

  const [products, setProducts] = useState(product_list);
  const [product, setProduct] = useState(null);

  return (
    <ProductsContext.Provider value={{products, setProducts}}>
      <ProductContext.Provider value={{product, setProduct}}>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<MainPage/>}>
              <Route path="/test1" element={<Component1/>}/>
              <Route path="/test2" element={<Component2/>}/>
              <Route path="/cart" element={<Cart/>}/>
              <Route path="/product_list" element={<ProductList/>}/>
              <Route path="/product" element={<ProductPage/>}/>
              <Route path="/" element={<ProductList/>}/>
            </Route>
          </Routes>
        </BrowserRouter>
      </ProductContext.Provider>
    </ProductsContext.Provider>
  )
}

export default App
