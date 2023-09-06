package com.pavlov.onlinestore;


import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

@RestController
public class ProductController {

    @GetMapping("/product/{param_1}/{param_2}")
    public String testSum(@PathVariable Long param_1, @PathVariable Long param_2) {
        return "" + (param_1 + param_2);
    }

    @SneakyThrows
    @GetMapping("/allproducts")
    public String getAllProducts() {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/online_store?" +
                                                                "user=root&password=");
        PreparedStatement statement = connection.prepareStatement("select id, name from product");
        ResultSet result_set = statement.executeQuery();
        String body = "";
        while (result_set.next()) {
            body += result_set.getInt("id");
            body += " ";
            body += result_set.getString("name");
            body += "\n";
        }
        return body;
    }

}
