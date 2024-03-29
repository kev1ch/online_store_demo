package com.pavlov.onlinestore.controllers;


import com.pavlov.onlinestore.model.Product;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    DataSource dataSource;

    @GetMapping("/sum/{param_1}/{param_2}")
    public String testSum(@PathVariable Long param_1, @PathVariable Long param_2) {
        return "" + (param_1 + param_2);
    }

    @SneakyThrows
    // @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<Product> getAllProducts() {
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
    @GetMapping("/")
    public Product getProduct(@RequestParam int id) {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("select id, name, " +
                "store_id, aisle, bay, stock_quantity from product where id = ?");
        statement.setInt(1, id);
        ResultSet result_set = statement.executeQuery();
        Product result = null;

        if (result_set.next()) {
            Product product = new Product();
            product.setId(result_set.getInt("id"));
            product.setName(result_set.getString("name"));
            product.setStore_id(result_set.getInt("store_id"));
            product.setAisle(result_set.getInt("aisle"));
            product.setBay(result_set.getInt("bay"));
            product.setStock_quantity(result_set.getInt("stock_quantity"));
        }

        return result;
    }

    @SneakyThrows
    @PostMapping("/")
    public String createProduct(@RequestParam String name, @RequestParam int store_id, @RequestParam int aisle,
                                @RequestParam int bay, @RequestParam int stock_quantity) {
        var connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO product (name, store_id, aisle, bay, stock_quantity) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, name);
        statement.setInt(2, store_id);
        statement.setInt(3, aisle);
        statement.setInt(4, bay);
        statement.setInt(5, stock_quantity);
        statement.execute();
        return "createProduct called";
    }

    @SneakyThrows
    @DeleteMapping("/")
    public String deleteProduct(@RequestParam int id) {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("delete from product where id = ?");
        statement.setInt(1, id);
        statement.execute();
        return "deleteProduct called";
    }

}
