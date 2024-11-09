package com.pavlov.onlinestore.unit;

import com.pavlov.onlinestore.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProductTest {

    @Test
    public void voidTest() {

    }

    @Test
    public void productTest() {
        Product product = new Product();

        product.setId(123);
        assert product.getId() == 123;

        product.setName("test_name");
        assert product.getName().equals("test_name");

        product.setStore_id(2);
        assert product.getStore_id() == 2;

        product.setAisle(3);
        assert product.getAisle() == 3;

        product.setBay(4);
        assert product.getBay() == 4;

        product.setStock_quantity(5);
        assert product.getStock_quantity() == 5;

        BigDecimal rate = new BigDecimal(12.34);
        product.setRate(rate);
        assert product.getRate().equals(rate);
    }

}
