package ru.practicum.stats.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class HitDto {
    String uri;

    Integer hit;

    public HitDto(String uri, Integer hit) {
        this.uri = uri;
        this.hit = hit;
    }
}
