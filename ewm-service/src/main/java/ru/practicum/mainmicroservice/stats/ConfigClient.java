package ru.practicum.mainmicroservice.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


//@Configuration
    @Component
    public class ConfigClient {

        private final String statServerUrl;

        public String getStatServerUrl() {
            System.out.println(statServerUrl + "ZZZ^");
            return statServerUrl;
        }

        @Autowired
        public ConfigClient(@Value("${stats-server.url}") String statServerUrl) {
            this.statServerUrl = statServerUrl;
        }

//        @Bean
//        public String statClient() {
//            return statServerUrl;
//        }

    }

