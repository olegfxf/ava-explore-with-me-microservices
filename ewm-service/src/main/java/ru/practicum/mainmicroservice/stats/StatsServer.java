package ru.practicum.mainmicroservice.stats;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import ru.practicum.client.HttpClient;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class StatsServer {

    static HttpClient httpClient = new HttpClient();

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static String dateTime = LocalDateTime.now().format(dateTimeFormatter);

    public static void saveHit(HttpServletRequest request) throws IOException, InterruptedException {
        String uri = request.getRequestURI();
        String ip = request.getHeader("host").split(":")[0];
        HttpResponse<String> httpResponse =
                httpClient.postHit("{\"app\":\"ewm-main-service\"," +
                        "\"uri\":\"" + uri + "\"," +
                        "\"ip\":\"" + ip + "\"," +
                        "\"timestamp\":\"" + dateTime + "\"}");
    }


    public static Integer requeryViews(String uris) throws IOException, InterruptedException {
        String start = LocalDateTime.now().minusYears(1).format(dateTimeFormatter);
        String end = LocalDateTime.now().plusYears(1).format(dateTimeFormatter);

        HttpResponse<String> httpResponse1 = Client.http(start, end, uris + "&unique=true"); //= "&unique=true")
        String jsonString = httpResponse1.body();
        ObjectMapper mapper = new ObjectMapper();
       List<Map<String, Object>> map = mapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});

        String jsonString2 = map.get(0).toString();
        int index = jsonString2.indexOf("hits=");
        int views = Integer.valueOf(jsonString2.substring(index + 5, jsonString2.length() - 1));

        return views;
    }

}
