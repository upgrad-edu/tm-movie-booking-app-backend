package com.upgrad.movieBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages= {"com.upgrad.movieBooking"})
@EntityScan(basePackages="com.upgrad.beans")
@EnableJpaRepositories(basePackages="com.upgrad.DAO")
public class MovieBookingApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieBookingApplication.class, args);
	}

}
