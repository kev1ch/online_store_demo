import { useEffect, useContext, useState } from "react"
import { ProductContext } from "./ProductContext"
import plug from "./img/logo.png";  //TODO replace image

function ProductImg(props) {

    //console.log(props.product_id);

    const {product, setProduct} = useContext(ProductContext);

    const [image, setImage] = useState("");

    useEffect(() => {
        async function loadImage() {
          
          try {
            const response = await(fetch("http://localhost:8081/product/product_display?product_id=" + props.product_id));
            
            if (response.ok) {
              const img_content = await(response.text());  
              setImage("data:image/png;base64," + img_content);
            } else {
              throw "error loading image from REST API";
            }

          } catch(exception) {
            setImage(plug);
          }

          //console.log(img_content);
    
        }
    
        loadImage();
    
      }, []);

    return(
        <img src={image} />
    )
}

export default ProductImg