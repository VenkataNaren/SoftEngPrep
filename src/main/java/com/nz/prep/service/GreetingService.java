package com.nz.prep.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreeting() {
        return "Hello from the Service layer!";
    }

    public String greet(String name) {
        return "Hello, " + name + "! from Service";
    }
}