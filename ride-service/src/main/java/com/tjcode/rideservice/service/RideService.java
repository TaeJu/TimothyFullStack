package com.tjcode.rideservice.service;

import com.tjcode.rideservice.entity.RideRequest;
import com.tjcode.rideservice.entity.Rider;
import com.tjcode.rideservice.repository.RideRequestRepository;
import com.tjcode.rideservice.repository.RiderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RideService {

    private static final Logger logger = LoggerFactory.getLogger(RideService.class);

    @Autowired
    private RideRequestRepository rideRequestRepository;

    @Autowired
    private RiderRepository riderRepository;

    public void registerRider(Rider rider) {
        riderRepository.save(rider);
        Optional<List<RideRequest>> rideRequests = rideRequestRepository.getUnassignedRideRequests();

        if (rideRequests.isPresent()) {
            Integer numAssigned = 0;
            Queue<RideRequest> rideRequestQueue = new LinkedList<>();
            Set<RideRequest> rideRequestSet = new HashSet<>();

            if (rideRequests.get().size() >= rider.getAvailableSpot()) {
                for (int i = 0; i < rider.getAvailableSpot(); i++) {
                    rideRequestQueue.add(rideRequests.get().get(i));
                }
                for (int i = 0; i < rider.getAvailableSpot(); i++) {
                    RideRequest rideRequest = rideRequestQueue.poll();
                    rideRequest.setRider(rider);
                    rideRequestRepository.save(rideRequest);
                    rideRequestSet.add(rideRequest);
                    numAssigned++;
                }
                rider.setRequests(rideRequestSet);
                rider.setCurrentFillSpot(numAssigned);
                riderRepository.save(rider);
            } else {
                for (int i = 0; i < rideRequests.get().size(); i++) {
                    rideRequestQueue.add(rideRequests.get().get(i));
                }
                for (int i = 0; i < rideRequests.get().size(); i++) {
                    RideRequest rideRequest = rideRequestQueue.poll();
                    rideRequest.setRider(rider);
                    rideRequestRepository.save(rideRequest);
                    rideRequestSet.add(rideRequest);
                    numAssigned++;
                }
                rider.setRequests(rideRequestSet);
                rider.setCurrentFillSpot(numAssigned);
                riderRepository.save(rider);
            }
        }
    }
}
