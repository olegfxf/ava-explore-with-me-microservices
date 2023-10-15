package ru.practicum.mainmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MainMicroserviseApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainMicroserviseApplication.class, args);
	}
}
