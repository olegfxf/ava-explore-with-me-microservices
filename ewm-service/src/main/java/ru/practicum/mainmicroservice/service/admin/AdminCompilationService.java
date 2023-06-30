package ru.practicum.mainmicroservice.service.admin;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainmicroservice.dto.*;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.Compilation;
import ru.practicum.mainmicroservice.model.Event;
import ru.practicum.mainmicroservice.repository.CompilationRepository;
import ru.practicum.mainmicroservice.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCompilationService {
    final CompilationRepository compilationRepository;
    final EventRepository eventRepository;

    @Autowired
    public AdminCompilationService(CompilationRepository compilationRepository, EventRepository eventRepository) {
        this.compilationRepository = compilationRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public CompilationDto saveAdminCompilation(NewCompilationDto newCompilationDto) {
        if (newCompilationDto.getEvents() == null) {
            newCompilationDto.setEvents(List.of(Long.MAX_VALUE));
        }

        List<Event> events = eventRepository.findAllById(newCompilationDto.getEvents());
        Compilation compilation = CompilationMapper.toCompilation(newCompilationDto, events);
        List<EventShortDto> eventShortDtos = compilation.getEvents().stream()
                .map(EventMapper::toEventShortDto).collect(Collectors.toList());

        log.debug(String.valueOf(LogMessages.ADD), "ПОДБОРКА СОБЫТИЙ");
        return CompilationMapper.toCompilationDto(compilationRepository.save(compilation), eventShortDtos);
    }

    @Transactional
    public CompilationDto deleteAdminCompilation(Long id) {
        log.debug(String.valueOf(LogMessages.REMOVE), "ПОДБОРКУ СОБЫТИЙ");
        Compilation compilation = compilationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Подборка не найдена"));
        compilationRepository.deleteById(id);
        log.debug(String.valueOf(LogMessages.REMOVE), id + " - ПОДБОРКА СОБЫТИЙ");
        return CompilationMapper.toCompilationDto(compilation, null);

    }

    @Transactional
    public CompilationDto updateAdminCompilation(Long id, UpdateCompilationRequest newCompilationDto) {
        Compilation compilation = compilationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Подборка с  id " + id + " не найдена"));

        List<Event> events = new ArrayList<>();
        if (newCompilationDto.getEvents() != null) {
            events = eventRepository.findAllById(newCompilationDto.getEvents());
            compilation.setEvents(events);
        }

        List<EventShortDto> eventShortDtos = events.stream().map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
        CompilationDto compilationDto = CompilationMapper.toCompilationDto(compilationRepository
                .save(compilation), eventShortDtos);

        log.debug(String.valueOf(LogMessages.PATCH), "ПОДБОРКА СОБЫТИЙ");
        return compilationDto;
    }
}
