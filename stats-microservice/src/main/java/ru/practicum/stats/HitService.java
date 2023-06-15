package ru.practicum.stats;

import ru.practicum.stats.model.Hit;

import java.util.List;

public interface HitService {
    Hit save(Hit hit);

    List<ViewStats> getStats(String startStr, String endStr, List<String> uris, Boolean isUnique);
}
