package com.tjcode.rideservice.repository;

import com.tjcode.rideservice.entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Integer> {

    @Query(value = "SELECT * FROM Ride_Request WHERE rider_id IS NULL", nativeQuery = true)
    Optional<List<RideRequest>> getUnassignedRideRequests();
}
