package ru.practicum.stats;

import ru.practicum.stats.dto.HitDto;
import ru.practicum.stats.model.Hit;

import java.util.List;

public interface HitService {
    Hit save(Hit hit);

    List<HitDto> getStats(String startStr, String endStr, Boolean isUnique);
}
