package com.tjcode.rideproducer.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RideRequest {
    private Integer rideRequestId;
    private String requestName;
    private String location;
}
