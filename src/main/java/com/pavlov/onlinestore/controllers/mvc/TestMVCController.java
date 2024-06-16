package com.pavlov.onlinestore.controllers.mvc;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestMVCController {

    @GetMapping("/test")
    public String test(@RequestParam(name = "name", required = false, defaultValue = "world") String name,
                       Model model, HttpSession http_session) {
        model.addAttribute("name", name);
        String is_logged_in = (String)http_session.getAttribute("logged_in");

        String result = "login";

        //if (is_logged_in != null && is_logged_in.equals("yes")) {
        if (true) {
            result = "test";
        }

        return result;
    }

}
