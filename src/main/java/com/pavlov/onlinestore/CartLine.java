package com.pavlov.onlinestore;

import lombok.Data;

@Data
public class CartLine {
    private int product_id;
    private int quantity;
}
