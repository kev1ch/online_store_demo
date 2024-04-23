package com.pavlov.onlinestore.dao;

import com.pavlov.onlinestore.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDAO {

    @Autowired
    DataSource dataSource;

    /*
    @Bean
    public CustomerDAO customerDAO1() {
        return new CustomerDAO();
    }
     */

    @SneakyThrows
    public List<User> allUsers() {
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
