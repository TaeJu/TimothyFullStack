package com.tjcode.rideservice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rideId;

    private String requestName;

    private String location;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="rider_id")
    private Rider rider;

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
