package com.rick.webservice.rick;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloECommController {

    @GetMapping("/ecomm")
    public String sayecomm() {
        return "Hello, EComm!";
    }

    @GetMapping("/ecomm/getproducts")
    public String getProducts() {
        return "Products";
    }
}
