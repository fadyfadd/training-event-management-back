package com.training.training_event_management_back;

import com.training.training_event_management_back.Repositories.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainingEventManagementBackApplication {

	@Bean

	CommandLineRunner test(EventRepository e) {
		return (args)->{

			int i;
			i=3;
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TrainingEventManagementBackApplication.class, args);
	}

}
