import { useContext } from "react"
import { ProductContext } from "./ProductContext"
import ProductImg from "./ProductImg";

function ProductPage() {

    const {product, setProduct} = useContext(ProductContext);

    return(
        <>
            <p>Product Page: {product.name}</p>
            <ProductImg product_id = {product.id}/>
        </>
    )
}

export default ProductPage