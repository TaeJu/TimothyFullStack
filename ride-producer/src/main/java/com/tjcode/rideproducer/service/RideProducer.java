package com.tjcode.rideproducer.service;

import com.tjcode.rideproducer.event.RideEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RideProducer {

    private static final Logger logger = LoggerFactory.getLogger(RideProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, RideEvent> kafkaTemplate;

    public RideProducer(NewTopic topic, KafkaTemplate<String, RideEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RideEvent event) {
        logger.info(String.format("Ride Event => %s", event.toString()));

        Message<RideEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
