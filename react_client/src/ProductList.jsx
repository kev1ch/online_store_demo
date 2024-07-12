import { useContext } from "react"
import { ProductsContext } from "./ProductsContext"
import { ProductContext } from "./ProductContext"
import { Link, useNavigate } from "react-router-dom";

function ProductList() {

    const navigate = useNavigate();
    const {products, setProducts} = useContext(ProductsContext);
    const {product, setProduct} = useContext(ProductContext);
    var product_quantity = products.length;


    return(

        <p>Product List:<br/> {products.map(this_product =>
            (<a onClick={()=>{
                console.log('navigate to product page');
                setProduct(this_product);
                navigate('/product');
                }}>
                {this_product.name}<br/></a>))}</p>

    )
}

export default ProductList;