package ru.practicum.mainmicroservice.service.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainmicroservice.dto.CompilationDto;
import ru.practicum.mainmicroservice.dto.CompilationMapper;
import ru.practicum.mainmicroservice.dto.EventMapper;
import ru.practicum.mainmicroservice.dto.EventShortDto;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.Compilation;
import ru.practicum.mainmicroservice.repository.CompilationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PublicCompilationService {
    private final CompilationRepository compilationRepository;

    public PublicCompilationService(CompilationRepository compilationRepository) {
        this.compilationRepository = compilationRepository;
    }

    public List<CompilationDto> getAllPublicCompilation(Boolean pinned, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);

        Page<Compilation> compilations;
        if (pinned != null)
            compilations = compilationRepository.findAllByPinned(pinned, PageRequest.of(from / size, size));
        else {
            compilations = compilationRepository.findAll(PageRequest.of(from / size, size));
        }

        List<CompilationDto> compilationDtos = new ArrayList<>();
        for (Compilation comp : compilations) {
            List<EventShortDto> eventShortDtos = comp.getEvents().stream()
                    .map(EventMapper::toEventShortDto).collect(Collectors.toList());
            compilationDtos.add(CompilationMapper.toCompilationDto(comp, eventShortDtos));
        }

        log.debug(String.valueOf(LogMessages.GET_ALL), "ПОДБОРОК СОБЫТИЙ");
        return compilationDtos;
    }


    public CompilationDto getByIdPublicCompilation(Long compId) {

        Compilation compilations = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("ПОДБОРОК СОБЫТИЙ С id " + compId + " НЕТ"));

        List<EventShortDto> eventShortDtos = compilations.getEvents().stream()
                .map(EventMapper::toEventShortDto).collect(Collectors.toList());

        log.debug(String.valueOf(LogMessages.GET_ALL), "ПОДБОРОК СОБЫТИЙ");

        return CompilationMapper.toCompilationDto(compilations, eventShortDtos);
    }


}
