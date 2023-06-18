package ru.practicum.client;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {
    String host = "localhost";
    String port = "9090";

    public HttpResponse<String> getStats(String start, String end) throws IOException, InterruptedException {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://{host}:{port}/stats")
                .queryParam("start", start)
                .queryParam("end", end)
                .build();
        URI uriGet = uriComponents.expand(host, port).toUri();

        HttpRequest requestEvent = HttpRequest.newBuilder()
                .uri(uriGet)
                .headers(HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT)
                .build();

        HttpResponse<String> responseStats = java.net.http.HttpClient.newBuilder()
                .build()
                .send(requestEvent, HttpResponse.BodyHandlers.ofString());

        return responseStats;

    }

    public HttpResponse<String> getStats(String start, String end, String uris) throws IOException, InterruptedException {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://{host}:{port}/stats")
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("uris", uris)
                .build();
        URI uriGet = uriComponents.expand(host, port).toUri();

        HttpRequest requestEvent = HttpRequest.newBuilder()
                .uri(uriGet)
                .headers(HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT)
                .build();

        HttpResponse<String> responseStats = java.net.http.HttpClient.newBuilder()
                .build()
                .send(requestEvent, HttpResponse.BodyHandlers.ofString());

        return responseStats;
    }

    public HttpResponse<String> getStats(String start, String end, String uris1, String uris2)
            throws IOException, InterruptedException {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://{host}:{port}/stats")
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("uris", uris1)
                .queryParam("uris", uris2)
                .build();
        URI uriGet = uriComponents.expand(host, port).toUri();

        HttpRequest requestEvent = HttpRequest.newBuilder()
                .uri(uriGet)
                .headers(HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT)
                .build();

        HttpResponse<String> responseStats = java.net.http.HttpClient.newBuilder()
                .build()
                .send(requestEvent, HttpResponse.BodyHandlers.ofString());

        return responseStats;
    }


    public HttpResponse<String> postHit(String hit) throws IOException, InterruptedException {

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("http://{host}:{port}/hit")
                .build();
        URI uriPost = uriComponents.expand(host, port).toUri();

        HttpRequest requestEvent = HttpRequest.newBuilder()
                .uri(uriPost)
                .headers(HttpHeaders.CONTENT_TYPE, "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(hit))
                .build();

        HttpResponse<String> responseEvent = java.net.http.HttpClient.newBuilder()
                .build()
                .send(requestEvent, HttpResponse.BodyHandlers.ofString());

        return responseEvent;
    }
}
