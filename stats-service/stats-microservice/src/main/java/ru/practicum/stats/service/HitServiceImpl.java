package ru.practicum.stats.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.dto.HitDto;
import ru.practicum.stats.HitRepository;
import ru.practicum.stats.dto.HitMapper;
import ru.practicum.stats.dto.ViewStats;
import ru.practicum.stats.model.Stats;
import ru.practicum.stats.service.HitService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HitServiceImpl implements HitService {
    private final HitRepository hitRepository;

    @Autowired
    public HitServiceImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    public HitDto save(Stats stats) {
        log.debug("Выполнен запрос на сохренение события");
        return HitMapper.toHitDto(hitRepository.save(stats));
    }


    public List<ViewStats> getStats(String startStr, String endStr, Optional<List<String>> uris, Boolean isUnique) {
        List<ViewStats> viewStats = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime start = LocalDateTime.parse(startStr, formatter);
        LocalDateTime end = LocalDateTime.parse(endStr, formatter);

        if (isUnique) {
            if (uris.isPresent())
                viewStats = hitRepository.getStatsWithUriDistinct(start, end, uris.get());
            else
                viewStats = hitRepository.getStatsWithoutUriDistinct(start, end);

        } else {
            if (uris.isPresent())
                viewStats = hitRepository.getStatsWithUri(start, end, uris.get());
            else
                viewStats = hitRepository.getStatsWithoutUri(start, end);
        }

        Collections.sort(viewStats, (d1, d2) -> {
            return d2.getHits().intValue() - d1.getHits().intValue();
        });
        log.debug("Выполнен запрос на предоставление статистики");
        return viewStats;
    }


}
