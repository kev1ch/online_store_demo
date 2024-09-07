package com.pavlov.onlinestore.controllers.rest;

import com.pavlov.onlinestore.dao.CustomerDAO;
import com.pavlov.onlinestore.model.AuthData;
import com.pavlov.onlinestore.model.Product;
import com.pavlov.onlinestore.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@RequestMapping("/account")
@RestController
public class UserController {

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomerDAO customerDAO;

    private static final String KEY = "mytestkey123456789wordjava987654321bitssize";    //TODO change secret key

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
    @PostMapping("/auth")
    public ResponseEntity<String> getToken(@RequestBody AuthData auth_data) {

        if (customerDAO.loginPasswordCheck(auth_data.getLogin(), auth_data.getPassword())) {
            Long time = System.currentTimeMillis();
            Date issued_date = new Date(time);
            Date expiration_date = new Date(time + 600000);
            String username = auth_data.getLogin();
            Map<String, Object> claims = new HashMap<>();
            String jwt = Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(issued_date).setExpiration(expiration_date).signWith(SignatureAlgorithm.HS256, KEY).compact();
            return ResponseEntity.status(HttpStatus.OK).body(jwt);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong login/password");
        }
    }

    @SneakyThrows
    // @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return customerDAO.allUsers();
    }

}