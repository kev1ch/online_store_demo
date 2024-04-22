package com.pavlov.onlinestore.controllers.rest;

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
    public String createUser(@RequestParam String email, @RequestParam String password, @RequestParam String first_name,
            @RequestParam String last_name, @RequestParam String phone_number, @RequestParam String address_line,
            @RequestParam String city, @RequestParam String postal_code, @RequestParam String state_province) {
        var connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO customer (email, passwrd, first_name, last_name, phone_number, address_line, " +
                        "city, postal_code, state_province) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setString(1, email);
        statement.setString(2, password);
        statement.setString(3, first_name);
        statement.setString(4, last_name);
        statement.setString(5, phone_number);
        statement.setString(6, address_line);
        statement.setString(7, city);
        statement.setString(8, postal_code);
        statement.setString(9, state_province);
        statement.execute();
        return "createUser called";
    }

    @SneakyThrows
    // @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("select id, email, first_name, " +
                "last_name, phone_number, address_line, city, postal_code, state_province from customer");
        ResultSet result_set = statement.executeQuery();
        List<User> result = new ArrayList<>();

        while (result_set.next()) {
            User user = new User();
            user.setId(result_set.getInt("id"));
            user.setEmail(result_set.getString("email"));
            user.setFirst_name(result_set.getString("first_name"));
            user.setLast_name(result_set.getString("last_name"));
            user.setPhone_number(result_set.getString("phone_number"));
            user.setAddress_line(result_set.getString("address_line"));
            user.setCity(result_set.getString("city"));
            user.setPostal_code(result_set.getString("postal_code"));
            user.setState_province(result_set.getString("state_province"));
            result.add(user);
        }

        return result;

    }

}