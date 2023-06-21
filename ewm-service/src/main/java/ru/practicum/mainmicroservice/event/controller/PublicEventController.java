package ru.practicum.mainmicroservice.event.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.mainmicroservice.event.dto.EventFullDto;
import ru.practicum.mainmicroservice.event.dto.EventShortDto;

import java.util.List;

@RestController
@RequestMapping("/events")
public class PublicEventController {

    public EventShortDto getAll(@RequestParam String text,
                                @RequestParam List<String> categories,
                                @RequestParam boolean paid,
                                @RequestParam String rangeStart,
                                @RequestParam String rangeEnd,
                                @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                @RequestParam String sort,
                                @RequestParam int from,
                                @RequestParam int size) {
        return null;
    }


    public EventFullDto getById(@RequestParam Long id) {
        return null;
    }


}
