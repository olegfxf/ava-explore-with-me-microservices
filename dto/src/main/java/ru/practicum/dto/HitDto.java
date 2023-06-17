package ru.practicum.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HitDto {
    String app;

    String uri;

    Integer hit;

//    public HitDto(String app, String uri, Integer hit) {
//        this.app = app;
//        this.uri = uri;
//        this.hit = hit;
//    }
}
