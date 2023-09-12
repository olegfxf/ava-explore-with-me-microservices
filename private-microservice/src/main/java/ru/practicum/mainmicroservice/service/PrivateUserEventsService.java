package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.event.EventFullDto;
import ru.practicum.mainmicroservice.dto.event.NewEventDto;
import ru.practicum.mainmicroservice.dto.event.UpdateEventUserRequest;
import ru.practicum.mainmicroservice.dto.request.EventRequestStatusUpdateRequest;
import ru.practicum.mainmicroservice.dto.request.EventRequestStatusUpdateResult;
import ru.practicum.mainmicroservice.dto.request.ParticipationRequestDto;

import java.util.List;

public interface PrivateUserEventsService {
    EventFullDto saveEvent(Long userId, NewEventDto newEventDto);

    List<EventFullDto> getAllEvents(Long userId, int from, int size);

    EventFullDto getByIdUsersEvens(Long userId, Long eventId);

    EventFullDto updatePrivateUsersEvents(Long userId, Long eventId,
                                          UpdateEventUserRequest newEventDto);

    List<ParticipationRequestDto> getByIdUsersEvensRequests(Long eventId, Long userId);

    EventRequestStatusUpdateResult updateUsersEventsRequests(Long userId, Long eventId,
                                                             EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);


}
