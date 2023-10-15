package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.event.EventFullDto;
import ru.practicum.mainmicroservice.dto.event.EventShortDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface PublicEventService {
    List<EventShortDto> getAllEvants(String text, List<Long> categories, Boolean paid, String rangeStart,
                                     String rangeEnd, Boolean onlyAvailable, String sort, Integer from,
                                     Integer size, HttpServletRequest request);

    EventFullDto getByIdEvents(Long eventId, HttpServletRequest request)
            throws IOException, InterruptedException;

}
