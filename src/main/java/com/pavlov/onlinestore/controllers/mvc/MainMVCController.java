package com.pavlov.onlinestore.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainMVCController {

    @GetMapping(value = {"/", "index"})
    public String mainPage() {

        return "index";
    }

    @GetMapping("/test_design")
    public String mainDesign() {

        return "fragments/main_design";
    }

}
