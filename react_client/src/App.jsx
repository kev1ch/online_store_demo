import './App.css'
import Component1 from './Component1'
import Component2 from './Component2'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import MainPage from './MainPage'
import ProductList from './ProductList'
import Cart from './Cart'
import product_list from './product_list'
import { useEffect, useState } from 'react'
import { ProductsContext } from './ProductsContext'
import { ProductContext } from './ProductContext'
import { CartContext } from './CartContext'
import ProductPage from './ProductPage'
import LogIn from './LogIn'
import { LogInContext } from './LogInContext'
import Admin from './Admin'

const useFetch = (url) => {
  const [data, setData] = useState(null);

  useEffect(() => {
    async function fetchData() {
      const response = await fetch(url);
      const json = await response.json();
      setData(json);
    }
    fetchData();
  }, [url]);

  return data;
};

function App() {

  const [products, setProducts] = useState(product_list);
  const [product, setProduct] = useState(null);
  const [cart, setCart] = useState([]);
  const [login, setLogin] = useState({login: false});
  // const result = useFetch("http://localhost:8081/product/all");

  useEffect(() => {
    async function loadData() {
      
      const response = await fetch("http://localhost:8081/product/all");
      
      if (response.ok) {

        const json = await(response.json());
        console.log("result: ", json);
        const product_list_2 = [];
        json.map(element => {
          
          const new_product = {
            id: element.id,
            name: element.name,
            price: element.rate,
            description: "test description"
          };

          product_list_2.push(new_product);
        });
        setProducts(product_list_2);
      } else {
        setProduct(product_list);
      }

    }

    loadData();

  }, []);

  return (
    <>
      <ProductsContext.Provider value={{products, setProducts}}>
        <ProductContext.Provider value={{product, setProduct}}>
          <CartContext.Provider value={{cart, setCart}}>
            <LogInContext.Provider value={{login, setLogin}}>
              <BrowserRouter>
                <Routes>
                  <Route path="/" element={<MainPage/>}>
                    <Route path="/test1" element={<Component1/>}/>
                    <Route path="/test2" element={<Component2/>}/>
                    <Route path="/cart" element={<Cart/>}/>
                    <Route path="/product_list" element={<ProductList/>}/>
                    <Route path="/product" element={<ProductPage/>}/>
                    <Route path="/login" element={<LogIn/>} />
                    <Route path="/admin" element={<Admin/>} />
                    <Route path="/" element={<ProductList/>}/>
                  </Route>
                </Routes>
              </BrowserRouter>
            </LogInContext.Provider>  
          </CartContext.Provider>
        </ProductContext.Provider>
      </ProductsContext.Provider>
    </>
  )
}

export default App;
