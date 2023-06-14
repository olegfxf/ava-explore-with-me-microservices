package ru.practicum.stats.dto;

import ru.practicum.stats.model.Hit;

public class HitMapper {
    public static Hit toHit(HitDto hitDto) {
        return Hit.builder()
                .uri(hitDto.getUri())
                .build();
    }

    public static HitDto toHitDto(Hit hit) {
        return HitDto.builder()
                .uri(hit.getUri())
                .build();
    }
}
