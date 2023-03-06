package com.tjcode.rideproducer.service;

import com.tjcode.rideproducer.entity.RideRequest;
import com.tjcode.rideproducer.repository.RideRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository rideRequestRepository;

    public RideRequest saveRideRequest(RideRequest rideRequest) {
        return rideRequestRepository.save(rideRequest);
    }
}
