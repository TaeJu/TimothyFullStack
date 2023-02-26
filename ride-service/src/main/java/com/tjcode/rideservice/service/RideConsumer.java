package com.tjcode.rideservice.service;

import com.tjcode.basedomains.entity.EmailEvent;
import com.tjcode.rideproducer.event.RideEvent;
import com.tjcode.rideservice.entity.RideRequest;
import com.tjcode.rideservice.entity.Rider;
import com.tjcode.rideservice.repository.RideRequestRepository;
import com.tjcode.rideservice.repository.RiderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RideConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RideConsumer.class);

    @Autowired
    private RideRequestRepository rideRequestRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private EmailProducer emailProducer;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(RideEvent rideEvent) {
        logger.info(String.format("Ride Event received in Ride Consumer Service => %s", rideEvent.toString()));

        EmailEvent emailEvent = new EmailEvent();
        Rider rider = riderRepository.getAvailableRider(rideEvent.getRideRequest().getLocation());
        RideRequest rideRequest;

        if (rider != null) {
            rideRequest = RideRequest.builder()
                    .requestName(rideEvent.getRideRequest().getRequestName())
                    .location(rideEvent.getRideRequest().getLocation())
                    .rider(rider)
                    .build();
            rider.setCurrentFillSpot(rider.getCurrentFillSpot() + 1);

            emailEvent.setRequestName(rideEvent.getRideRequest().getRequestName());
            emailEvent.setLocation(rideRequest.getLocation());
            emailEvent.setRiderInfo(rideRequest.getRider().getName());

            riderRepository.save(rider);
        } else {
            logger.info("No Available Rider At Your Location");
            rideRequest = RideRequest.builder()
                    .requestName(rideEvent.getRideRequest().getRequestName())
                    .location(rideEvent.getRideRequest().getLocation())
                    .build();

            emailEvent.setRequestName(rideEvent.getRideRequest().getRequestName());
            emailEvent.setLocation(rideRequest.getLocation());
            emailEvent.setRiderInfo("N/A");
        }

        rideRequestRepository.save(rideRequest);

        // send Message to EailService
        emailProducer.sendMessage(emailEvent);
    }
}
