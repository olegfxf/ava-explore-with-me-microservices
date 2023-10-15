package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.compilation.CompilationDto;
import ru.practicum.mainmicroservice.dto.compilation.NewCompilationDto;
import ru.practicum.mainmicroservice.dto.compilation.UpdateCompilationRequest;

public interface AdminCompilationService {
    CompilationDto saveAdminCompilation(NewCompilationDto newCompilationDto);

    void deleteAdminCompilation(Long id);

    CompilationDto updateAdminCompilation(Long id, UpdateCompilationRequest newCompilationDto);

}
