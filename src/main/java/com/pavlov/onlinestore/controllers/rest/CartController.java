package com.pavlov.onlinestore.controllers.rest;

import com.pavlov.onlinestore.model.CartLine;
import com.pavlov.onlinestore.model.Product;
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
    public List<CartLine> getAllLinesFromCart(@RequestParam int cart_id) {
        return null;
    }


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
        PreparedStatement statement = connection.prepareStatement("insert into cart_line (cart_id, product_id," +
                " quantity) values (?, ?, ?)");
        statement.setInt(1, cart_id);
        statement.setInt(2, product_id);
        statement.setInt(3, quantity);
        statement.execute();
        return 0;
    }

    @SneakyThrows
    // @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<CartLine> getAllLines() {
        PreparedStatement statement = dataSource.getConnection().prepareStatement("select cl.cart_id, p.id, p.name, " +
                "cl.quantity, pri.rate from cart_line cl, product p, price pri where cart_id = 1 and p.id = cl.product_id " +
                "and pri.product_id = p.id;");
        ResultSet result_set = statement.executeQuery();
        List<CartLine> result = new ArrayList<>();

        while (result_set.next()) {
            CartLine cart_line = new CartLine();
            cart_line.setCart_id(1);    //TODO change for multiple cartlines
            cart_line.setProduct_id(result_set.getInt("id"));
            cart_line.setProduct_name(result_set.getString("name"));
            cart_line.setQuantity(result_set.getInt("quantity"));
            cart_line.setPrice(result_set.getBigDecimal("rate"));
            result.add(cart_line);
        }

        return result;

    }

}
