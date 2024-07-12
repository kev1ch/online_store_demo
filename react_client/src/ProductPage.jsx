import { useContext } from "react"
import { ProductContext } from "./ProductContext"

function ProductPage() {

    const {product, setProduct} = useContext(ProductContext);

    return(
        <p>Product Page: {product.name}</p>
    )
}

export default ProductPage