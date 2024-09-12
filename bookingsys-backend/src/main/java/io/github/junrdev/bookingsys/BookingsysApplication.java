package io.github.junrdev.bookingsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BookingsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingsysApplication.class, args);
	}

}
