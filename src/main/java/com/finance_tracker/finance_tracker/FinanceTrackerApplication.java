package com.finance_tracker.finance_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceTrackerApplication {

	public static void main(String[] args) {
		System.out.println("Current user: " + System.getProperty("user.name"));

		SpringApplication.run(FinanceTrackerApplication.class, args);

	}

}
