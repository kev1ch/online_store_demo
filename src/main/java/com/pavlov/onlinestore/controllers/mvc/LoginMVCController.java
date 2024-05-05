package com.pavlov.onlinestore.controllers.mvc;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginMVCController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "") String email,
                            @RequestParam(required = false, defaultValue = "") String password,
                            Model model, HttpSession http_session) {

        String result = "login";

        if (email.equals("test@email.com") && password.equals("12345")) {
            http_session.setAttribute("logged_in", "yes");
            result = "test";
        } else {
            http_session.setAttribute("logged_in", "no");
        }

        return result;
    }

    @GetMapping("/logout")
    public String logoutPage(Model model, HttpSession http_session) {
        http_session.setAttribute("logged_in", "no");
        return "login";
    }

}
