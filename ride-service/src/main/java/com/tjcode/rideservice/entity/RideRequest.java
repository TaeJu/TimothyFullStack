package com.tjcode.rideservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RideRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rideId;

    private String requestName;

    private String location;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rider_id", referencedColumnName = "rider_id")
    private Rider rider;

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rider_id", referencedColumnName = "rider_id")
    @JsonBackReference
    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RideRequest that = (RideRequest) o;
        return Objects.equals(rideId, that.rideId) && Objects.equals(requestName, that.requestName) && Objects.equals(location, that.location) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(lastUpdated, that.lastUpdated) && Objects.equals(rider, that.rider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rideId, requestName, location, dateCreated, lastUpdated, rider);
    }
}
