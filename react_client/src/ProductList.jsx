import { useContext } from "react"
import { ProductsContext } from "./ProductsContext"
import { ProductContext } from "./ProductContext"
import { CartContext } from "./CartContext";
import './ProductList.css';
import { Link, useNavigate } from "react-router-dom";
import ProductImg from "./ProductImg";

function ProductList() {

    const navigate = useNavigate();
    const {products, setProducts} = useContext(ProductsContext);
    const {product, setProduct} = useContext(ProductContext);
    const {cart, setCart} = useContext(CartContext);
    var product_quantity = products.length;


    return(

        <div className="productList">Product List: {products.map(this_product =>
            (<div className="oneProduct">
                <div className="productName" onClick={()=>{
                console.log('navigate to product page');
                setProduct(this_product);
                navigate('/product');
                }}>
                <ProductImg product_id = {this_product.id}/>
                {this_product.name}</div>
                <div className="productPrice">${this_product.price}</div>
                <div className="productDescription">{this_product.description}</div>
                <button onClick={()=>{
                    const cart_copy = [...cart];
                    const cart_line = cart_copy.find(cart_line => cart_line.id === this_product.id);
                    if (!cart_line) {
                        cart_copy.push({...this_product, quantity: 1});
                    } else {
                        cart_line.quantity += 1;
                    }
                    setCart(cart_copy);
                    /*navigate('/cart')*/}}>Add to Cart</button>
                </div>))}</div>

    )
}

export default ProductList;