package ru.practicum.mainmicroservice.dto;

import ru.practicum.mainmicroservice.model.Compilation;
import ru.practicum.mainmicroservice.model.Event;

import java.util.List;

public class CompilationMapper {
    public static Compilation toCompilation(NewCompilationDto newCompilationDto,
                                            List<Event> events) {
        Boolean pinned = newCompilationDto.getPinned() == null ? false : newCompilationDto.getPinned();
        return Compilation.builder()
                .events(events)
                .pinned(pinned)
                .title((newCompilationDto.getTitle()))
                .build();
    }

    public static CompilationDto toCompilationDto(Compilation compilation,
                                                  List<EventShortDto> eventShortDtos) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .events(eventShortDtos)
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }
}
