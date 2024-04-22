package com.pavlov.onlinestore.model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String address_line;
    private String city;
    private String postal_code;
    private String state_province;

}
