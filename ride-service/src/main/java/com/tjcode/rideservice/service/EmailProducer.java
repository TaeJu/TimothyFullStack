package com.tjcode.rideservice.service;

import com.tjcode.basedomains.entity.EmailEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

    private static final Logger logger = LoggerFactory.getLogger(EmailProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, EmailEvent> kafkaTemplate;

    public EmailProducer(NewTopic topic, KafkaTemplate<String, EmailEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EmailEvent event) {
        logger.info(String.format("Email event => %s", event.toString()));

        Message<EmailEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
