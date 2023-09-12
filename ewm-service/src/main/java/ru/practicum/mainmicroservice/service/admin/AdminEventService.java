package ru.practicum.mainmicroservice.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainmicroservice.dto.EventFullDto;
import ru.practicum.mainmicroservice.dto.EventMapper;
import ru.practicum.mainmicroservice.dto.UpdateEventAdminRequest;
import ru.practicum.mainmicroservice.exception.BadRequestException;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.Event;
import ru.practicum.mainmicroservice.model.State;
import ru.practicum.mainmicroservice.model.Status;
import ru.practicum.mainmicroservice.repository.EventRepository;
import ru.practicum.mainmicroservice.repository.LocationRepository;
import ru.practicum.mainmicroservice.repository.RequestRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminEventService {
    final EventRepository eventRepository;
    final LocationRepository locationRepository;
    final RequestRepository requestRepository;

    public AdminEventService(EventRepository eventRepository,
                             LocationRepository locationRepository,
                             RequestRepository requestRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.requestRepository = requestRepository;
    }

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public EventFullDto updateAdminEvents(Long eventId, UpdateEventAdminRequest adminRequest) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие с id " + eventId + "не найдено"));

        LocalDateTime timeEvent = LocalDateTime.now();
        if (adminRequest != null && adminRequest.getEventDate() != null) {
            timeEvent = LocalDateTime.parse(adminRequest.getEventDate(), dateTimeFormatter);
            if (timeEvent.isBefore(LocalDateTime.now().plusHours(1)))
                throw new BadRequestException("Событие должно начаться минимум через час");
        }

        if (event.getState() != null && !event.getState().equals(State.PENDING)) {
            throw new IllegalStateException("Событие можно публиковать, если оно находится в сосотянии PENDING");
        }

        Optional.ofNullable(adminRequest.getAnnotation()).ifPresent(event::setAnnotation);
        Optional.ofNullable(adminRequest.getDescription()).ifPresent(event::setDescription);
        event.setEventDate(timeEvent);
        if (adminRequest.getLocation() != null)
            locationRepository.save(adminRequest.getLocation());
        event.setLocation(adminRequest.getLocation());
        Optional.ofNullable(adminRequest.getPaid()).ifPresent(event::setPaid);
        Optional.ofNullable(adminRequest.getParticipantLimit()).ifPresent(event::setParticipantLimit);
        Optional.ofNullable(adminRequest.getRequestModeration()).ifPresent(event::setRequestModeration);
        if (adminRequest.getStateAction() != null)
            adminRequest.setStateAction(adminRequest.getStateAction().equals("PUBLISH_EVENT") ? "PUBLISHED" : adminRequest.getStateAction());
        if (adminRequest.getStateAction() != null)
            adminRequest.setStateAction(adminRequest.getStateAction().equals("REJECT_EVENT") ? "CANCELED" : adminRequest.getStateAction());
        if (adminRequest.getStateAction() != null)
            Optional.ofNullable(State.valueOf(adminRequest.getStateAction())).ifPresent(event::setState);
        Optional.ofNullable(adminRequest.getTitle()).ifPresent(event::setTitle);

        EventFullDto returnEventFullDto = EventMapper.toEventFullDto(eventRepository.save(event));

        log.debug(String.valueOf(LogMessages.UPDATE), "СОБЫТИЕ");
        return returnEventFullDto;
    }


    public List<EventFullDto> getAdminEvents(List<Long> userIds, List<String> states, List<Long> categoryIds,
                                             String rangeStart, String rangeEnd, int from, int size) {
        List<State> stateList = states == null ? null : states
                .stream()
                .map(State::valueOf)
                .collect(Collectors.toList());

        Pageable pageable = (Pageable) PageRequest.of(from / size, size);
        LocalDateTime start = rangeStart == null ? null : LocalDateTime.parse(rangeStart, dateTimeFormatter);
        LocalDateTime end = rangeEnd == null ? null : LocalDateTime.parse(rangeEnd, dateTimeFormatter);
        List<EventFullDto> events = eventRepository.searchAdminEvents2(userIds, stateList, categoryIds,
                        start, end, pageable)
                .stream().map(EventMapper::toEventFullDto).collect(Collectors.toList());

        List<EventFullDto> eventFullDtoList = new ArrayList<>();
        EventFullDto event = new EventFullDto();
        for (EventFullDto iter : events) {
            event = iter;
            Long eventId = iter.getId();
            Integer confirmedRequest = requestRepository.countByEventIdAndStatus(eventId,
                    Status.CONFIRMED);
            event.setConfirmedRequests(confirmedRequest);
            eventFullDtoList.add(event);
        }

        log.debug(String.valueOf(LogMessages.GET_ALL), "СОБЫТИЙ");
        return eventFullDtoList;
    }

}
