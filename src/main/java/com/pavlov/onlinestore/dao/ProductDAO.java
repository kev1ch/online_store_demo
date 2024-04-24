package com.pavlov.onlinestore.dao;

import com.pavlov.onlinestore.model.Product;
import com.pavlov.onlinestore.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {

    @Autowired
    DataSource dataSource;

    @SneakyThrows
    public List<Product> allProducts() {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("select id, name, " +
                "store_id, aisle, bay, stock_quantity from product");
        ResultSet result_set = statement.executeQuery();
        List<Product> result = new ArrayList<>();

        while (result_set.next()) {
            Product product = new Product();
            product.setId(result_set.getInt("id"));
            product.setName(result_set.getString("name"));
            product.setStore_id(result_set.getInt("store_id"));
            product.setAisle(result_set.getInt("aisle"));
            product.setBay(result_set.getInt("bay"));
            product.setStock_quantity(result_set.getInt("stock_quantity"));
            result.add(product);
        }

        return result;
    }

    @SneakyThrows
    public Product getProductById(int id) {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("select id, name, " +
                "store_id, aisle, bay, stock_quantity from product where id = ?");
        statement.setInt(1, id);
        ResultSet result_set = statement.executeQuery();
        Product given_product = null;

        if (result_set.next()) {
            Product product = new Product();
            product.setId(result_set.getInt("id"));
            product.setName(result_set.getString("name"));
            product.setStore_id(result_set.getInt("store_id"));
            product.setAisle(result_set.getInt("aisle"));
            product.setBay(result_set.getInt("bay"));
            product.setStock_quantity(result_set.getInt("stock_quantity"));
            given_product = product;
        }

        return given_product;
    }

}
