package ru.practicum.mainmicroservice.controller.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.CompilationDto;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.pub.PublicCompilationService;

import java.util.List;

@Slf4j
@RequestMapping("/compilations")
@RestController
public class PublicCompilationController {

    private final PublicCompilationService publicCompilationService;

    public PublicCompilationController(PublicCompilationService publicCompilationService) {
        this.publicCompilationService = publicCompilationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompilationDto> getAllPublicCompilation(@RequestParam(required = false) Boolean pinned,
                                                        @RequestParam(defaultValue = "0") Integer from,
                                                        @RequestParam(defaultValue = "10") Integer size) {
        log.debug(String.valueOf(LogMessages.TRY_GET_ALL), "ПОДБОРОК СОБЫТИЙ");
        return publicCompilationService.getAllPublicCompilation(pinned, from, size);
    }


    @GetMapping("/{compId}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto getByIdPublicCompilation(@PathVariable Long compId) {
        log.debug(String.valueOf(LogMessages.TRY_GET_ALL), "ПОДБОРОК СОБЫТИЙ");
        return publicCompilationService.getByIdPublicCompilation(compId);
    }


}
