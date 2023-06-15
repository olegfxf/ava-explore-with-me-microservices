package ru.practicum.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.stats.model.Hit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HitServiceImpl implements HitService {
    HitRepository hitRepository;

    @Autowired
    public HitServiceImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    public Hit save(Hit hit) {
        return hitRepository.save(hit);
    }


    public List<ViewStats> getStats(String startStr, String endStr, List<String> uris, Boolean isUnique) {
        List<ViewStats> viewStats = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime start = LocalDateTime.now();
        if (startStr != null)
            start = LocalDateTime.parse(startStr, formatter);
        LocalDateTime end = LocalDateTime.now();
        if (endStr != null)
            end = LocalDateTime.parse(endStr, formatter);

        if (isUnique) {
            if (uris == null)
                viewStats = hitRepository.getStatsWithoutUriDistinct(start, end);
            else
                viewStats = hitRepository.getStatsWithUriDistinct(start, end, uris);
        } else {
            if (uris == null)
                viewStats = hitRepository.getStatsWithoutUri(start, end);
            else
                viewStats = hitRepository.getStatsWithUri(start, end, uris);
        }

        Collections.reverse(viewStats);
        return viewStats;
    }
}
