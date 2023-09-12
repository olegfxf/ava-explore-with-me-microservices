package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.compilation.CompilationDto;

import java.util.List;

public interface PublicCompilationService {
    List<CompilationDto> getAllPublicCompilation(Boolean pinned, Integer from, Integer size);

    CompilationDto getByIdPublicCompilation(Long compId);
}
