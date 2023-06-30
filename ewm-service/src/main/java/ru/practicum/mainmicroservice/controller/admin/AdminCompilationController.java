package ru.practicum.mainmicroservice.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.CompilationDto;
import ru.practicum.mainmicroservice.dto.NewCompilationDto;
import ru.practicum.mainmicroservice.dto.UpdateCompilationRequest;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.admin.AdminCompilationService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/compilations")
public class AdminCompilationController {
    @Autowired
    public AdminCompilationController(AdminCompilationService adminCompilationService) {
        this.adminCompilationService = adminCompilationService;
    }

    private final AdminCompilationService adminCompilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto saveAdminCompilation(
            @RequestBody @Valid NewCompilationDto newCompilationDto) {
        log.debug(String.valueOf(LogMessages.TRY_ADD), "ПОДБОРКА СОБЫТИЙ");
        return adminCompilationService.saveAdminCompilation(newCompilationDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompilationDto deleteAdminCompilation(@PathVariable Long id) {
        log.debug(String.valueOf(LogMessages.TRY_REMOVE_OBJECT), "ПОДБОРКИ СОБЫТИЙ");
        return adminCompilationService.deleteAdminCompilation(id);
    }

    @PatchMapping("/{id}")
    public CompilationDto updateAdminCompilation(@PathVariable Long id,
                                                 @RequestBody @Valid UpdateCompilationRequest updateCompilationRequest) {
        log.debug(String.valueOf(LogMessages.TRY_UPDATE), "ПОДБОРКА СОБЫТИЙ");
        return adminCompilationService.updateAdminCompilation(id, updateCompilationRequest);
    }

}
