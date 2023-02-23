package com.tjcode.emailservice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmailFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer emailId;

    private String requestName;
    private String location;
    private String riderName;
    private String emailBody;

    @CreationTimestamp
    private LocalDateTime timeCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
