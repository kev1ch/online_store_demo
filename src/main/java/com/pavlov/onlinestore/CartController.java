package com.pavlov.onlinestore;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    DataSource dataSource;

    // getAllLinesFromCart

    // deleteLineFromCart

    // getTotalPrice

    // changeProductQuantity

    // add line to cart
    @SneakyThrows
    @PutMapping("/add")
    public Integer addProducts(@RequestParam int cart_id, @RequestParam int product_id, @RequestParam int quantity) {

        // create newCartLine
        // add cart_line into cart
        // insert into cart_line (id, cart_id, product_id, quantity) values (1, 1, 1, 1);
        var connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into cart_line (cart_id, product_id, quantity) values (?, ?, ?)");
        statement.setInt(1, cart_id);
        statement.setInt(2, product_id);
        statement.setInt(3, quantity);
        statement.execute();
        return 0;
    }

}
