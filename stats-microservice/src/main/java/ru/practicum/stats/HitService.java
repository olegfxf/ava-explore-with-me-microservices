package ru.practicum.stats;

import ru.practicum.dto.HitDto;
import ru.practicum.stats.dto.ViewStats;
import ru.practicum.stats.model.Hit;

import java.util.List;
import java.util.Optional;

public interface HitService {

    HitDto save(Hit hit);

    List<ViewStats> getStats(String startStr, String endStr, Optional<List<String>> uris, Boolean isUnique);
}
