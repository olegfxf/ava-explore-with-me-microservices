package ru.practicum.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.dto.HitDto;
import ru.practicum.stats.dto.HitMapper;
import ru.practicum.stats.dto.ViewStats;
import ru.practicum.stats.model.Hit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class HitServiceImpl implements HitService {
    HitRepository hitRepository;

    @Autowired
    public HitServiceImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    public HitDto save(Hit hit) {
        return HitMapper.toHitDto(hitRepository.save(hit));
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

        Collections.reverse(viewStats);
        return viewStats;
    }
}
