package com.flight.search.amadeus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // for scheduled jobs
@OpenAPIDefinition(info = @Info(title = "Library APIs",version = "1.0"))
public class AmadeusApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmadeusApplication.class, args);
	}

}
