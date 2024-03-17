package com.pavlov.onlinestore.controllers;

import com.pavlov.onlinestore.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

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
}