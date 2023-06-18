package ru.practicum.stats.dto;

import ru.practicum.dto.HitDto;
import ru.practicum.stats.model.Stats;

public class HitMapper {
    public static Stats toHit(HitDto hitDto) {
        return Stats.builder()
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                .build();
    }

    public static HitDto toHitDto(Stats stats) {
        return HitDto.builder()
                .app(stats.getApp())
                .uri(stats.getUri())
                .build();
    }
}
