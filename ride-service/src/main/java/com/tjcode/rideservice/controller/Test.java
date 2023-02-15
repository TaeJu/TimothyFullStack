package com.tjcode.rideservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ride-service")
public class Test {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/test")
    public String test() {
        return "test get call";
    }
}
