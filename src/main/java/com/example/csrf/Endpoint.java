package com.example.csrf;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom-endpoint")
public class Endpoint {

    @PostMapping
    public String customEndpoint() {
        return "custom endpoint";
    }

}
