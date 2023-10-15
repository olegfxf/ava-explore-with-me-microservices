package ru.practicum.mainmicroservice.stats;

import org.springframework.stereotype.Component;
import ru.practicum.client.HttpClient;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.Stats;
import ru.practicum.dto.ViewStats;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class StatsServer {

    static HttpClient httpClient = new HttpClient();

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static String dateTime = LocalDateTime.now().format(dateTimeFormatter);

    ConfigClient configClient;

    FeiginClient feiginClient;

    public StatsServer(ConfigClient configClient,
                       FeiginClient feiginClient) {
        this.configClient = configClient;
        this.feiginClient = feiginClient;
    }

    public HitDto saveHit(HttpServletRequest request) throws IOException, InterruptedException {
        String host = configClient.getStatServerUrl();
        String uri = request.getRequestURI();
        String ip = request.getHeader("host").split(":")[0];


//        HttpResponse<String> httpResponse =
//                httpClient.postHit(host, "{\"app\":\"ewm-main-service\"," +
//                        "\"uri\":\"" + uri + "\"," +
//                        "\"ip\":\"" + ip + "\"," +
//                        "\"timestamp\":\"" + dateTime + "\"}");

        Stats stats = new Stats();
        stats.setApp("ewm-main-service");
        stats.setIp(ip);
        stats.setUri(uri);
        stats.setTimestamp(LocalDateTime.now());
        HitDto hitDto = feiginClient.save(stats);

        System.out.println(hitDto + " ZZZ@ " + host);

        return hitDto;
    }

    public Integer requeryViews(String uris) throws IOException, InterruptedException {
        String start = LocalDateTime.now().minusYears(1).format(dateTimeFormatter);
        String end = LocalDateTime.now().plusYears(1).format(dateTimeFormatter);

        System.out.println("ZZZ5  ");
        List<ViewStats> viewStats = feiginClient.getStats(start, end, List.of(uris), true); //Optional.of(List.of(uris))
        System.out.println(uris + "  ZZZ5  " + viewStats);

//        String host = configClient.getStatServerUrl();
//        HttpResponse<String> httpResponse1 = Client.http(start, end, host, uris + "&unique=true"); //= "&unique=true")
//        String jsonString = httpResponse1.body();
//        ObjectMapper mapper = new ObjectMapper();
//        List<Map<String, Object>> map = mapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {
//        });
//
//        String jsonString2 = map.get(0).toString();
//        int index = jsonString2.indexOf("hits=");
//        int views = Integer.valueOf(jsonString2.substring(index + 5, jsonString2.length() - 1));
//        System.out.println(viewStats.get(0).getHits() + "  views!  " + views);
//        return views;
        return Math.toIntExact(viewStats.get(0).getHits());
    }

}
