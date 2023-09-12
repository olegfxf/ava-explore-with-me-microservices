package ru.practicum.mainmicroservice.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.mainmicroservice.dto.compilation.CompilationDto;
import ru.practicum.mainmicroservice.dto.compilation.NewCompilationDto;
import ru.practicum.mainmicroservice.dto.event.EventShortDto;
import ru.practicum.mainmicroservice.model.Compilation;
import ru.practicum.mainmicroservice.model.Event;

import java.util.List;

@UtilityClass
public class CompilationMapper {
    public Compilation toCompilation(NewCompilationDto newCompilationDto,
                                     List<Event> events) {
        Boolean pinned = newCompilationDto.getPinned() == null ? false : newCompilationDto.getPinned();
        return Compilation.builder()
                .events(events)
                .pinned(pinned)
                .title((newCompilationDto.getTitle()))
                .build();
    }

    public CompilationDto toCompilationDto(Compilation compilation,
                                           List<EventShortDto> eventShortDtos) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .events(eventShortDtos)
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }
}
