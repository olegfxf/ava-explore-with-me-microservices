package ru.practicum.mainmicroservice.stats;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    public static HttpResponse<String> http(String start, String end, String uris)
            throws IOException, InterruptedException {

        String host = "localhost";
        String port = "9090";
        String uris1 = "/" + uris;
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://{host}:{port}/stats")
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("uris1", uris)
                .build();
        URI uriGet = uriComponents.expand(host, port).toUri();
        //       System.out.println("ZZZ8  " + uriGet.toString());
//http://localhost:9090/stats?start=2020-05-05%2000:00:00&end=2035-05-05%2000:00:00&uris=/events/1&uris=/events/2
        HttpRequest request = null;

        request = HttpRequest.newBuilder()
                .GET()
                .uri(uriGet)
                .header("content-type", "application/json")
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, handler);

        return response;
    }
}