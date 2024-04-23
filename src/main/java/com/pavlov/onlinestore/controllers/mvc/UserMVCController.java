package com.pavlov.onlinestore.controllers.mvc;

import com.pavlov.onlinestore.dao.CustomerDAO;
import com.pavlov.onlinestore.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class UserMVCController {

    @Autowired
    CustomerDAO customerDAO;

    @SneakyThrows
    @GetMapping("/all_customers")
    public String getAll(Model model) {
        List<User> all_users = customerDAO.allUsers();
        model.addAttribute("users", all_users);
        return "user_table_server.html";
    }

}
