package com.tjcode.rideproducer.controller;

import com.tjcode.rideproducer.entity.RideRequest;
import com.tjcode.rideproducer.event.RideEvent;
import com.tjcode.rideproducer.service.RideProducer;
import com.tjcode.rideproducer.service.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ride-producer")
public class RideProducerController {

    @Autowired
    private RideProducer rideProducer;

    @Autowired
    private RideRequestService rideRequestService;

    @PostMapping("/rideRequest")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public String requestRide(@RequestBody RideRequest rideRequest) {
        RideEvent rideEvent = new RideEvent();
        rideEvent.setStatus("PENDING");
        rideEvent.setMessage("Ride request status is in pending state");
        rideEvent.setRideRequest(rideRequest);

        rideProducer.sendMessage(rideEvent);

        rideRequestService.saveRideRequest(rideRequest);

        return "Ride request has been placed successfully";
    }
}
