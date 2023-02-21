package com.tjcode.rideproducer.event;

import com.tjcode.rideproducer.entity.RideRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RideEvent {
    public String message;
    public String status;
    public RideRequest rideRequest;
}
