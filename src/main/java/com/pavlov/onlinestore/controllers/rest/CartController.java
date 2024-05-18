package com.pavlov.onlinestore.controllers.rest;

import com.pavlov.onlinestore.dao.ProductDAO;
import com.pavlov.onlinestore.model.CartLine;
import com.pavlov.onlinestore.model.Product;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/cart")
@RestController
public class CartController {

    private static final String HTTP_SESSION_CART_KEY = "session_cart";

    @Autowired
    ProductDAO productDAO;

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
    @PostMapping("/temp_cart")
    public Integer addToTempCart(@RequestParam int product_id, @RequestParam int quantity, HttpSession http_session) {

        Object cart_obj = http_session.getAttribute(HTTP_SESSION_CART_KEY);
        Map<Integer, Integer> cart;
        if (cart_obj == null) {
            cart = new HashMap<>();
        } else {
            cart = (Map<Integer, Integer>)cart_obj;
        }

        cart.put(product_id, quantity);
        http_session.setAttribute(HTTP_SESSION_CART_KEY, cart);

        return 0;
    }

    @SneakyThrows
    @GetMapping("/temp_cart")
    public List<CartLine> getFromTempCart(HttpSession http_session) {

        List<CartLine> result = new ArrayList<>();
        Object cart_obj = http_session.getAttribute(HTTP_SESSION_CART_KEY);

        if (cart_obj != null) {
            Map<Integer, Integer> cart = (Map<Integer, Integer>)cart_obj;
            for (Integer product_id : cart.keySet()) {
                if (cart.get(product_id) > 0) {
                    CartLine cart_line = new CartLine();
                    cart_line.setQuantity(cart.get(product_id));
                    cart_line.setProduct_id(product_id);
                    cart_line.setCart_id(0);

                    Product product = productDAO.getProductById(product_id);
                    cart_line.setProduct_name(product.getName());
                    cart_line.setPrice(new BigDecimal(0)); //TODO set price

                    result.add(cart_line);
                }
            }
        }

        return result;
    }

    @SneakyThrows
    @GetMapping("/temp_cart_lines_quantity")
    public Integer getLinesQuantity(HttpSession http_session) {

        int result = 0;
        Object cart_obj = http_session.getAttribute(HTTP_SESSION_CART_KEY);
        if (cart_obj != null) {
            Map<Integer, Integer> cart = (Map<Integer, Integer>)cart_obj;
            for (Integer product_id : cart.keySet()) {
                if (cart.get(product_id) > 0) {
                    result++;
                }
            }
        }

        return result;
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
