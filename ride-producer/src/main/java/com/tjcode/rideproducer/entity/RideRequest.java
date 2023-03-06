package com.tjcode.rideproducer.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "ride-request")
public class RideRequest {
    @Id
    private String rideRequestId;
    private String requestName;
    private String location;
}
