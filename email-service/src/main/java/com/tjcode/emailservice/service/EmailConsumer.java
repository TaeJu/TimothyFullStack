package com.tjcode.emailservice.service;

import com.tjcode.basedomains.entity.EmailEvent;
import com.tjcode.emailservice.constant.EmailBody;
import com.tjcode.emailservice.entity.EmailFormat;
import com.tjcode.emailservice.repository.EmailFormatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    @Autowired
    private EmailFormatRepository emailFormatRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(EmailEvent event) {
        logger.info(String.format("Email Event Received in Email Service => %s", event.toString()));

        EmailFormat emailFormat;

        if (!event.getRiderInfo().equals(EmailBody.riderCheck)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(EmailBody.emailSuccessBody);
            stringBuilder.append(event.getRiderInfo());

            emailFormat = EmailFormat.builder()
                    .requestName(event.getRequestName())
                    .location(event.getLocation())
                    .riderName(event.getRiderInfo())
                    .emailBody(stringBuilder.toString())
                    .build();
        } else {
            emailFormat = EmailFormat.builder()
                    .requestName(event.getRequestName())
                    .location(event.getLocation())
                    .riderName(event.getRiderInfo())
                    .emailBody(EmailBody.emailPendingBody)
                    .build();
        }

        emailFormatRepository.save(emailFormat);
        emailSenderService.sendMail("ltj8316@gmail.com", "Follow Up Email On RideRequest",emailFormat.getEmailBody());
        logger.info("Email sent successfully");
    }
}
