package com.tjcode.rideservice.repository;

import com.tjcode.rideservice.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Integer> {

    @Query(value = "SELECT * FROM Rider WHERE Rider_Status = TRUE AND current_fill_spot < available_spot AND preferred_location = ?1 LIMIT 1", nativeQuery = true)
    Rider getAvailableRider(String location);

    @Query(value = "SELECT * FROM Rider WHERE Rider_Status = TRUE AND current_fill_spot < available_spot", nativeQuery = true)
    Optional<List<Rider>> getAvailableAssignedRider();
}
