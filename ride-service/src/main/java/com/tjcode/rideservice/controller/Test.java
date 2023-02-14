package com.tjcode.rideservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ride-service")
public class Test {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }
}
