package com.pavlov.onlinestore;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SimpleController2 {

    @GetMapping("/datetime")
    public String testMethod() {
        return "" + new Date();
    }

}
