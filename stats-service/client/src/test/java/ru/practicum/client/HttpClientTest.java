package ru.practicum.client;

import junit.framework.TestCase;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;

public class HttpClientTest extends TestCase {
    HttpClient httpClient = new HttpClient();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        httpClient.postHit("http://localhost:9090","{\"app\":\"ewm-main-service\",\"uri\":\"/events/1\",\"ip\":\"121.0.0.1\",\"timestamp\":\"2023-06-16 10:48:51\"}");
        httpClient.postHit("http://localhost:9090","{\"app\":\"ewm-main-service\",\"uri\":\"/events/2\",\"ip\":\"121.0.0.1\",\"timestamp\":\"2023-06-16 10:48:52\"}");
        httpClient.postHit("http://localhost:9090","{\"app\":\"ewm-main-service\",\"uri\":\"/events\",\"ip\":\"121.0.0.1\",\"timestamp\":\"2023-06-16 10:48:50\"}");
    }


    @Test
    public void testPostHit() throws IOException, InterruptedException {
        HttpResponse<String> httpResponse =
                httpClient.postHit("http://localhost:9090","{\"app\":\"ewm-main-service\",\"uri\":\"/events/1\",\"ip\":\"121.0.0.1\",\"timestamp\":\"2023-06-16 10:48:51\"}");
        assertEquals(httpResponse.body(), "{\"app\":\"ewm-main-service\",\"uri\":\"/events/1\",\"hit\":null}");
        httpResponse = httpClient.postHit("http://localhost:9090","{\"app\":\"ewm-main-service\",\"uri\":\"/events/2\",\"ip\":\"121.0.0.1\",\"timestamp\":\"2023-06-16 10:48:52\"}");
        assertEquals(httpResponse.statusCode(), HttpURLConnection.HTTP_OK);
        httpResponse = httpClient.postHit("http://localhost:9090","{\"app\":\"ewm-main-service\",\"uri\":\"/events\",\"ip\":\"121.0.0.1\",\"timestamp\":\"2023-06-16 10:48:50\"}");
        assertEquals(httpResponse.statusCode(), HttpURLConnection.HTTP_OK);
    }
}