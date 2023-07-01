package ru.practicum.mainmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practicum.mainmicroservice.stats.ConfigClient;

@SpringBootApplication
public class MainPublicApplication1 {

	//public static void main(String[] args) {
	//	SpringApplication.run(MainPublicApplication1.class, args);
//	}
	public static void main(String[] args) {

		SpringApplication.run(MainMicroserviseApplication.class, args);

	}


}
