package com.training.training_event_management_back;

import com.training.training_event_management_back.Entities.Event;
import com.training.training_event_management_back.Repositories.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TrainingEventManagementBackApplication {

	/*@Bean

	CommandLineRunner test(EventRepository repo) {
		return (args)->{
			System.out.println("App started!");
			Event event = new Event();
			event.setTitle("Sample Training");
			event.setDescription("Test");
			event.setStartDate(LocalDate.parse("2025-06-07"));
			event.setEndDate(LocalDate.parse("2025-07-25"));
			event.setMaxStudent(25L);
			repo.save(event);
		};
	}
	*/

	public static void main(String[] args) {
		SpringApplication.run(TrainingEventManagementBackApplication.class, args);
	}

}
