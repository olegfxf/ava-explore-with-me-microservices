package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.event.EventFullDto;
import ru.practicum.mainmicroservice.dto.event.UpdateEventAdminRequest;

import java.util.List;

public interface AdminEventService {
    EventFullDto updateAdminEvents(Long eventId, UpdateEventAdminRequest adminRequest);

    List<EventFullDto> getAdminEvents(List<Long> userIds, List<String> states, List<Long> categoryIds,
                                      String rangeStart, String rangeEnd, int from, int size);

}
