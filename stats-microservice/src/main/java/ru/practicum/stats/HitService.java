package ru.practicum.stats;

import ru.practicum.stats.model.Hit;

import java.util.List;
import java.util.Optional;

public interface HitService {

    Hit save(Hit hit);

    List<ViewStats> getStats(String startStr, String endStr, Optional<List<String>> uris, Boolean isUnique);
}
