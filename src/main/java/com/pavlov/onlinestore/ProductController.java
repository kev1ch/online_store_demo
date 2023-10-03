package com.pavlov.onlinestore;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
        PreparedStatement statement = dataSource.getConnection().prepareStatement("select id, name from product");
        ResultSet result_set = statement.executeQuery();
        List<Product> result = new ArrayList<>();

        while (result_set.next()) {
            Product product = new Product();
            product.setId(result_set.getInt("id"));
            product.setName(result_set.getString("name"));
            result.add(product);
        }

        return result;

    }

    @SneakyThrows
    @PostMapping("/")
    public String createProduct(@RequestParam String name, @RequestParam int store_id) {
        var connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO product (name, store_id) VALUES (?, ?);");
        statement.setString(1, name);
        statement.setInt(2, store_id);
        statement.execute();
        return "createProduct called";
    }

    @SneakyThrows
    @DeleteMapping("/")
    public String deleteProduct(@RequestParam int id) {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/online_store?" +
                "user=root&password=");
        PreparedStatement statement = connection.prepareStatement("delete from product where id = ?");
        statement.setInt(1, id);
        statement.execute();
        return "deleteProduct called";
    }

}
