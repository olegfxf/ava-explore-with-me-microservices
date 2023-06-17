package ru.practicum.client;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello World!");

//        String hit = "{\"app\":\"ewm-main-service\",\"uri\":\"/events\",\"ip\":\"121.0.0.1\",\"timestamp\":\"2023-06-16 10:48:50\"}".replace("\\", "");
//        String hit1 = "{\"app\":\"ewm-main-service\",\"uri\":\"/events/1\",\"ip\":\"121.0.0.1\",\"timestamp\":\"2023-06-16 10:48:51\"}";
//        String hit2 = "{\"app\":\"ewm-main-service\",\"uri\":\"/events/2\",\"ip\":\"121.0.0.1\",\"timestamp\":\"2023-06-16 10:48:52\"}";
//
//        HttpClient httpClient = new HttpClient();
//        httpClient.postHit(hit);
//        httpClient.postHit(hit1);
//        httpClient.postHit(hit2);
//
//        httpClient.getStats("2020-05-05 00:00:00", "2035-05-05 00:00:00");
//        httpClient.getStats("2020-05-05 00:00:00", "2035-05-05 00:00:00", "/events/1");
//        httpClient.getStats("2020-05-05 00:00:00", "2035-05-05 00:00:00", "/events/1)", "/events/2");
////http://localhost:9090/stats?start=2020-05-05%2000:00:00&end=2035-05-05%2000:00:00&uris=/events/1&uris=/events/2
////http://localhost:9090/stats?start=2020-05-05%2000:00:00&end=2035-05-05%2000:00:00&uris=/events/1&uris=/events/2

    }
}
