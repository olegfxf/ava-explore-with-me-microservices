package ru.practicum.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.HitDto;
import ru.practicum.stats.dto.ViewStats;
import ru.practicum.stats.model.Stats;
import ru.practicum.stats.service.HitServiceImpl;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class HitController {
    private final HitServiceImpl hitService;

    public HitController(HitServiceImpl hitService) {
        this.hitService = hitService;
    }

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public HitDto save(@RequestBody Stats stats) {
        log.debug("Поступил запрос на создание события {}", stats);
        return hitService.save(stats);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<ViewStats> getStats(@RequestParam String start,
                                    @RequestParam String end,
                                    @RequestParam(required = false) Optional<List<String>> uris,
                                    @RequestParam(defaultValue = "false") boolean unique) {
        log.debug("Поступил запрос на предоставление статистики");
        return hitService.getStats(start, end, uris, unique);
    }

}
