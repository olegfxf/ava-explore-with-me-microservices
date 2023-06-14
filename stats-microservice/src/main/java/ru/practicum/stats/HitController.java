package ru.practicum.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stats.dto.HitDto;
import ru.practicum.stats.model.Hit;

import java.util.List;


@RestController
public class HitController {
    HitServiceImpl hitService;

    @Autowired
    public HitController(HitServiceImpl hitService) {
        this.hitService = hitService;
    }

    @PostMapping("/hit")
    @ResponseBody
    public Hit save(@RequestBody Hit hit) {
        return hitService.save(hit);
    }

    @GetMapping("/hit")
    public List<HitDto> getStats(@RequestParam String start,
                                 @RequestParam String end,
                            //     @RequestParam List<String> uris,
                                 @RequestParam Boolean isUnique) {
        return hitService.getStats(start, end, isUnique);
    }

}
