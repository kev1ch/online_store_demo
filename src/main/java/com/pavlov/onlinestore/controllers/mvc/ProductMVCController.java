package com.pavlov.onlinestore.controllers.mvc;

import com.pavlov.onlinestore.dao.ProductDAO;
import com.pavlov.onlinestore.model.Product;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

@Controller
public class ProductMVCController {

    @Autowired
    ProductDAO productDAO;

    @SneakyThrows
    @GetMapping("/all_products")
    public String getAll(Model model) {
        List<Product> all_products = productDAO.allProducts();
        model.addAttribute("products", all_products);
        return "product_table_server.html";
    }

    @SneakyThrows
    @GetMapping("/product_info")
    @Secured({"USER", "ADMIN"})
    public String getInfo(@RequestParam int id, Model model) {

        Product given_product = productDAO.getProductById(id);
        if (given_product != null) {
            model.addAttribute("product_id", given_product.getId());
            model.addAttribute("product_name", given_product.getName());
            model.addAttribute("stock_quantity", given_product.getStock_quantity());
        }

        return "product_info.html";
    }

    @SneakyThrows
    @GetMapping("/add_product")
    @Secured({"ADMIN"})
    public String add(@RequestParam(name = "product_id", required = false, defaultValue = "-1") int id, Model model) {

        boolean product_found = false;

        if (id != -1) {

            Product given_product = productDAO.getProductById(id);
            product_found = given_product != null;

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

    @SneakyThrows
    @GetMapping("/upload_image")
    public String uploadImage(Model model) {

        return "img_upload_form";
    }

    @SneakyThrows
    @PostMapping("/img_upload_receiver")
    public String imgUploadReceiver(@RequestParam("img_file")MultipartFile img_file, @RequestParam("product_id")int product_id) {
        String result = "";
        try (InputStream is = img_file.getInputStream()) {
            byte[] bytes = IOUtils.toByteArray(is);
            result = Base64.getEncoder().encodeToString(bytes);
            productDAO.saveImage(product_id, result);
        }
        return "img_upload_result";
    }

}
