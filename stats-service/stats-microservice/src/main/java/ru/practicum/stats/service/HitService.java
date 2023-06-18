package ru.practicum.stats.service;

import ru.practicum.dto.HitDto;
import ru.practicum.stats.dto.ViewStats;
import ru.practicum.stats.model.Stats;

import java.util.List;
import java.util.Optional;

public interface HitService {

    HitDto save(Stats stats);

    List<ViewStats> getStats(String startStr, String endStr, Optional<List<String>> uris, Boolean isUnique);
}
