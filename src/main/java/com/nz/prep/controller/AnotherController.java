package com.nz.prep.controller;

import com.nz.prep.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnotherController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/another-hello")
    public String anotherHello() {
        return greetingService.getGreeting();
    }

    @GetMapping("/greet/{name}")
    public String greet(String name) {
        return greetingService.greet(name);
    }
}