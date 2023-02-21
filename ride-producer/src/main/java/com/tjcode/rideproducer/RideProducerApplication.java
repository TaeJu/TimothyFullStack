package com.tjcode.rideproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RideProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideProducerApplication.class, args);
	}

}
