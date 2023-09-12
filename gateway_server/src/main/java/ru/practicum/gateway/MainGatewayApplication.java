package ru.practicum.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication
@EnableEurekaClient
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
//@EnableResourceServer
public class MainGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainGatewayApplication.class, args);
    }


}
