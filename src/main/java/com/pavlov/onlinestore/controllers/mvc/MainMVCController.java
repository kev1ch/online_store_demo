package com.pavlov.onlinestore.controllers.mvc;

import com.pavlov.onlinestore.dao.ProductDAO;
import com.pavlov.onlinestore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainMVCController {

    @Autowired
    ProductDAO productDAO;

    @GetMapping(value = {"/", "index"})
    public String mainPage(Model model) {
        List<Product> all_products = productDAO.allProducts();
        model.addAttribute("products", all_products);
        return "products_for_sale";
    }

    @GetMapping("/test_design")
    public String mainDesign() {

        return "main_design";
    }

}
