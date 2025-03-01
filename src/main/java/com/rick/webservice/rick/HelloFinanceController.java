package com.rick.webservice.rick;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloFinanceController {

    @GetMapping("/finance/hellofinance")
    public String sayFinance() {
        return "Hello, Finance!";
    }

    @GetMapping("/finance/finances")
    public String getFinances() {
        return "Finances";
    }
}
