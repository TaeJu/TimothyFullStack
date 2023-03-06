package com.tjcode.rideproducer.repository;

import com.tjcode.rideproducer.entity.RideRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RideRequestRepository extends MongoRepository<RideRequest, String> {
}
