package com.pavlov.onlinestore.controllers.mvc;

import com.pavlov.onlinestore.model.Product;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
public class ProductMVCController {

    @Autowired
    DataSource dataSource;

    @SneakyThrows
    @GetMapping("/add_product")
    public String add(@RequestParam(name = "product_id", required = false, defaultValue = "-1") int id, Model model) {

        boolean product_found = false;

        if (id != -1) {
            PreparedStatement statement = dataSource.getConnection().prepareStatement("select id, name, " +
                    "store_id, aisle, bay, stock_quantity from product where id = ?");
            statement.setInt(1, id);
            ResultSet result_set = statement.executeQuery();
            Product given_product = null;

            if (result_set.next()) {
                Product product = new Product();
                product.setId(result_set.getInt("id"));
                product.setName(result_set.getString("name"));
                product.setStore_id(result_set.getInt("store_id"));
                product.setAisle(result_set.getInt("aisle"));
                product.setBay(result_set.getInt("bay"));
                product.setStock_quantity(result_set.getInt("stock_quantity"));
                given_product = product;
                product_found = true;
            }

            if (given_product != null) {
                model.addAttribute("product_name", given_product.getName());
                model.addAttribute("aisle", given_product.getAisle());
                model.addAttribute("bay", given_product.getBay());
                model.addAttribute("stock_quantity", given_product.getStock_quantity());
            }
        }

        if (product_found) {
            model.addAttribute("id", String.valueOf(id));
        } else {
            model.addAttribute("id", String.valueOf(-1));
        }

        return "add_product";
    }

}
