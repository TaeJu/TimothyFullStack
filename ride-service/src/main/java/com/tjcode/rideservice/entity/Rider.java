package com.tjcode.rideservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Rider implements Serializable {

    @Id
    @Column(name = "rider_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer riderId;

    private String name;

    private String preferredLocation;

    private Integer availableSpot;

    private Integer currentFillSpot;

    private Boolean riderStatus;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="rider", cascade = CascadeType.ALL)
    private Set<RideRequest> requests = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "rider_image",
        joinColumns = {
            @JoinColumn(name = "rider_id")
        },
            inverseJoinColumns = {
                @JoinColumn(name = "image_id")
            }
    )
    private ImageModel imageModel;

    public ImageModel getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public Integer getRiderId() {
        return riderId;
    }

    public void setRiderId(Integer riderId) {
        this.riderId = riderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public Integer getAvailableSpot() {
        return availableSpot;
    }

    public void setAvailableSpot(Integer availableSpot) {
        this.availableSpot = availableSpot;
    }

    public Integer getCurrentFillSpot() {
        return currentFillSpot;
    }

    public void setCurrentFillSpot(Integer currentFillSpot) {
        this.currentFillSpot = currentFillSpot;
    }

    public Boolean getRiderStatus() {
        return riderStatus;
    }

    public void setRiderStatus(Boolean riderStatus) {
        this.riderStatus = riderStatus;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy="rider", cascade = CascadeType.ALL)
    @JsonManagedReference
    public Set<RideRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<RideRequest> requests) {
        this.requests = requests;
    }
}
