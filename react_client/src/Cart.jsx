import { CartContext } from "./CartContext"
import { useContext } from "react";

import './Cart.css';

function Cart() {

    const {cart, setCart} = useContext(CartContext);
    const cart_length = cart.length;

    return(
        <>
            <p>Cart</p>
            <p>{cart_length}</p>
            <table>
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Cost</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {cart.map(cart_line => (
                        <tr>
                            <td>{cart_line.id}</td>
                            <td>{cart_line.name}</td>
                            <td>{cart_line.description}</td>
                            <td><input type="number" min="1" value={cart_line.quantity} onChange={(event)=>{
                                const cart_copy = [...cart];
                                const product_line_quantity = cart_copy.find(product_line_quantity => product_line_quantity.id === cart_line.id);
                                product_line_quantity.quantity = parseInt(event.target.value);
                                setCart(cart_copy);
                            }}/></td>
                            <td>${cart_line.price.toFixed(2)}</td>
                            <td>${(cart_line.quantity * cart_line.price).toFixed(2)}</td>
                            <td class="tooltip"><a id="delete_x" onClick={() => {
                                const cart_copy = [...cart];
                                const cart_line_to_delete = cart_copy.find(cart_line_to_delete => cart_line_to_delete.id === cart_line.id);
                                cart_copy.splice(cart_copy.indexOf(cart_line_to_delete), 1);
                                setCart(cart_copy);
                                }}>X</a>
                            <span class="tooltiptext">Remove cart line</span></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    )
}

export default Cart