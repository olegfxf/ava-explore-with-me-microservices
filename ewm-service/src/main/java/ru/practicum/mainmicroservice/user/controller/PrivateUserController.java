package ru.practicum.mainmicroservice.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.event.dto.EventFullDto;
import ru.practicum.mainmicroservice.event.dto.NewEventDto;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.user.service.PrivateUserService;

@RestController
@Slf4j
@RequestMapping("/users/")
public class PrivateUserController {
    PrivateUserService privateUserService;

    @Autowired
    public PrivateUserController(PrivateUserService privateUserService) {
        this.privateUserService = privateUserService;
    }

    @PostMapping("{id}/events")
    public EventFullDto saveEvent(@PathVariable Long id,
                                  @RequestBody NewEventDto newEventDto) {
        log.debug(String.valueOf(LogMessages.TRY_ADD), "События");
        System.out.println(" QQQ2");

        return privateUserService.saveEvent(id, newEventDto);
    }
}
