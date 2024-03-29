package com.tjcode.rideservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RideServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideServiceApplication.class, args);
	}

}
