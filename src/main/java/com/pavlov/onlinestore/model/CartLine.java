package com.pavlov.onlinestore.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartLine {
    private int product_id;
    private int quantity;
    private int cart_id;
    private String product_name;
    private BigDecimal rate;
    private BigDecimal price;


    // select cl.cart_id, p.id, p.name, cl.quantity, pri.rate from cart_line cl,
    // product p, price pri where cart_id = 1 and p.id = cl.product_id and pri.product_id = p.id;
}
