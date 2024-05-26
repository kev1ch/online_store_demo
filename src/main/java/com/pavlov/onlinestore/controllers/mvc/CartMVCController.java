package com.pavlov.onlinestore.controllers.mvc;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartMVCController {

    @SneakyThrows
    @GetMapping("/cart")
    public String getCart() {
        return "cart.html";
    }

}
