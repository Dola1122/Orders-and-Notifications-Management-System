package com.example.software.assignment2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}
