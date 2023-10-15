package ru.practicum.mainmicroservice.dto.compilation;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.dto.event.EventShortDto;

import java.util.List;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {
    Long id;

    List<EventShortDto> events;

    Boolean pinned;

    String title;

}
