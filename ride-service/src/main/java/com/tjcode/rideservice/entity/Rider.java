package com.tjcode.rideservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer riderId;

    private String name;

    private String preferredLocation;

    private Integer availableSpot;

    private Integer currentFillSpot;

    private Boolean riderStatus;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @JsonIgnore
    @OneToMany(mappedBy="rider", cascade = CascadeType.ALL)
    private Set<RideRequest> requests = new HashSet<>();
}
