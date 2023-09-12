package ru.practicum.mainmicroservice.stats;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.Stats;
import ru.practicum.dto.ViewStats;

import java.util.List;

@FeignClient(name = "feigin-client", url = ("${stats-server.url}"))
public interface FeiginClient {
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public List<ViewStats> getStats(@RequestParam String start,
                                    @RequestParam String end,
                                    @RequestParam(required = false) List<String> uris,
                                    @RequestParam(defaultValue = "false") boolean unique);


    @RequestMapping(value = "/hit", method = RequestMethod.POST)
    public HitDto save(@RequestBody Stats stats);
}
