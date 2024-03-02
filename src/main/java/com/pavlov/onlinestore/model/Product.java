package com.pavlov.onlinestore.model;

import lombok.Data;

@Data
public class Product {

    private int id;
    private String name;
    private int store_id;
    private int aisle;
    private int bay;
    private int stock_quantity;

}
