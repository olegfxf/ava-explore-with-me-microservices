package ru.practicum.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stats.dto.HitDto;
import ru.practicum.stats.dto.HitMapper;
import ru.practicum.stats.dto.ViewStats;
import ru.practicum.stats.model.Hit;

import java.util.List;
import java.util.Optional;


@RestController
public class HitController {
    HitServiceImpl hitService;

    @Autowired
    public HitController(HitServiceImpl hitService) {
        this.hitService = hitService;
    }

    @PostMapping("/hit")
    public HitDto save(@RequestBody Hit hit) {

        return hitService.save(hit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(@RequestParam String start,
                                    @RequestParam String end,
                                    @RequestParam(required = false) Optional<List<String>> uris,
                                    @RequestParam(defaultValue = "false") boolean unique) {

        return hitService.getStats(start, end, uris, unique);
    }

}
