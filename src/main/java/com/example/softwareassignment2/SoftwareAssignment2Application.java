// SoftwareAssignment2Application.java
package com.example.softwareassignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SoftwareAssignment2Application {
	public static void main(String[] args) {
		SpringApplication.run(SoftwareAssignment2Application.class, args);
	}
}
