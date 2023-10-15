package ru.practicum.mainmicroservice.stats;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ConfigClient {
    private final String statServerUrl;
    private String dataSource;
    private String statUrl;

    public ConfigClient(@Value("${stats-server.url}") String statServerUrl,
                        @Value("${spring.datasource.url}") String dataSourse,
                        @Value("${stats-server.url}") String statUrl) {
        this.statServerUrl = statServerUrl;
        this.dataSource = dataSourse;
        this.statUrl = statUrl;
    }



    public String getStatServerUrl() {
        System.out.println(dataSource + " ZZZ2");
        System.out.println(statUrl + " ZZZ3");
        System.out.println(statServerUrl + " ZZZ4");
        return statServerUrl;
    }




}

