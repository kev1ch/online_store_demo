package com.pavlov.onlinestore.controllers.rest;


import com.pavlov.onlinestore.dao.ProductDAO;
import com.pavlov.onlinestore.model.Product;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    DataSource dataSource;

    @Autowired
    ProductDAO productDAO;

    @GetMapping("/sum/{param_1}/{param_2}")
    public String testSum(@PathVariable Long param_1, @PathVariable Long param_2) {
        return "" + (param_1 + param_2);
    }

    @SneakyThrows
    // @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productDAO.allProducts();
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
            result = product;
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
    @PutMapping("/")
    public String updateProduct(@RequestParam int id, @RequestParam String name, @RequestParam int store_id,
                                @RequestParam int aisle, @RequestParam int bay, @RequestParam int stock_quantity) {

        var connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE product SET name = ?, store_id = ?, aisle = ?, bay = ?, stock_quantity = ? WHERE id = ?");

        statement.setString(1, name);
        statement.setInt(2, store_id);
        statement.setInt(3, aisle);
        statement.setInt(4, bay);
        statement.setInt(5, stock_quantity);
        statement.setInt(6, id);
        statement.execute();
        return "updateProduct called";
    }

    @SneakyThrows
    @DeleteMapping("/")
    public String deleteProduct(@RequestParam int id) {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("delete from product where id = ?");
        statement.setInt(1, id);
        statement.execute();
        return "deleteProduct called";
    }

    @SneakyThrows
    @GetMapping("/product_display")
    public String displayProductById(@RequestParam int id) {
        String result = "";
        result = productDAO.getImage(id);
        return result;
    }

}
