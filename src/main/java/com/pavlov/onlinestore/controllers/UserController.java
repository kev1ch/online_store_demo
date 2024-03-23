package com.pavlov.onlinestore.controllers;

import com.pavlov.onlinestore.model.Product;
import com.pavlov.onlinestore.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/account")
@RestController
public class UserController {

    @Autowired              // ???
    DataSource dataSource; // ???

    @SneakyThrows
    @PostMapping("/register")
    public String createUser(@RequestParam String email, @RequestParam String password) {
        var connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO customer (email, passwrd) VALUES (?, ?);");
        statement.setString(1, email);
        statement.setString(2, password);
        statement.execute();
        return "createUser called";
    }

    @SneakyThrows
    // @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("select id, email from customer");
        ResultSet result_set = statement.executeQuery();
        List<User> result = new ArrayList<>();

        while (result_set.next()) {
            User user = new User();
            user.setId(result_set.getInt("id"));
            user.setEmail(result_set.getString("email"));
            result.add(user);
        }

        return result;

    }

}