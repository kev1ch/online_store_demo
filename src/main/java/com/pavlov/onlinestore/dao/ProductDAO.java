package com.pavlov.onlinestore.dao;

import com.pavlov.onlinestore.model.Product;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {

    @Autowired
    DataSource dataSource;

    @SneakyThrows
    public List<Product> allProducts() {

        List<Product> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT pro.id, name, store_id, aisle, " +
                    "bay, stock_quantity, rate FROM PRODUCT pro, PRICE pri WHERE pro.ID = pri.PRODUCT_ID;");
            ResultSet result_set = statement.executeQuery();

            while (result_set.next()) {
                Product product = new Product();
                product.setId(result_set.getInt("id"));
                product.setName(result_set.getString("name"));
                product.setStore_id(result_set.getInt("store_id"));
                product.setAisle(result_set.getInt("aisle"));
                product.setBay(result_set.getInt("bay"));
                product.setStock_quantity(result_set.getInt("stock_quantity"));
                product.setRate(result_set.getBigDecimal("rate"));
                result.add(product);
            }

            statement.close();
        }

        return result;
    }

    @SneakyThrows
    public Product getProductById(int id) {
        Product given_product = null;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select id, name, " +
                    "store_id, aisle, bay, stock_quantity from product where id = ?");
            statement.setInt(1, id);
            ResultSet result_set = statement.executeQuery();

            if (result_set.next()) {
                Product product = new Product();
                product.setId(result_set.getInt("id"));
                product.setName(result_set.getString("name"));
                product.setStore_id(result_set.getInt("store_id"));
                product.setAisle(result_set.getInt("aisle"));
                product.setBay(result_set.getInt("bay"));
                product.setStock_quantity(result_set.getInt("stock_quantity"));
                given_product = product;
            }

            statement.close();
        }

        return given_product;
    }

    @SneakyThrows
    public BigDecimal getRateByProductId(int id) {

        BigDecimal result = new BigDecimal(0);
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select rate from price where " +
                    "product_id = ?");
            statement.setInt(1, id);
            ResultSet result_set = statement.executeQuery();

            if (result_set.next()) {
                result = result_set.getBigDecimal("rate");
            }

            statement.close();
        }

        return result;
    }

    @SneakyThrows
    public String getImage(int img_id) {
        String result = "";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select content from product_img where " +
                    "id = ?");
            statement.setInt(1, img_id);
            ResultSet result_set = statement.executeQuery();

            if (result_set.next()) {
                result = result_set.getString("content");
            }

            statement.close();
        }
        return result;
    }

    @SneakyThrows
    public int saveImage(int product_id, String content) {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into product_img (content, product_id) " +
                    "values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, content);
            statement.setInt(2, product_id);
            int rows_affected = statement.executeUpdate();
            if (rows_affected > 0) {
                ResultSet generated_keys = statement.getGeneratedKeys();
                if (generated_keys.next()) {
                    result = generated_keys.getInt(1);
                }
            }
            statement.close();
        }
        return result;
    }

    @SneakyThrows
    public List<Integer> getAllProductImages(int product_id) {
        List<Integer> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select id from product_img where " +
                    "product_id = ?");
            statement.setInt(1, product_id);
            ResultSet result_set = statement.executeQuery();

            while (result_set.next()) {
                int img_id = result_set.getInt(1);
                result.add(img_id);
            }

            statement.close();
        }

        return result;
    }

}
