package com.tjcode.rideservice.controller;

import com.tjcode.rideservice.entity.Rider;
import com.tjcode.rideservice.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride-service")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/test")
    public String test() {
        return "test get call";
    }

    @PostMapping("/registerRider")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Rider> registerRider(@RequestBody Rider rider) {
        rideService.registerRider(rider);
        return ResponseEntity.ok(rider);
    }
}
