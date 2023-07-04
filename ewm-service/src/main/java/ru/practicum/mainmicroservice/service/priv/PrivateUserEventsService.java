package ru.practicum.mainmicroservice.service.priv;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainmicroservice.dto.*;
import ru.practicum.mainmicroservice.exception.BadRequestException;
import ru.practicum.mainmicroservice.exception.ConflictException;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.*;
import ru.practicum.mainmicroservice.repository.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrivateUserEventsService {
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    final UserRepository userRepository;
    final EventRepository eventRepository;
    final CategoryRepository categoryRepository;
    final LocationRepository locationRepository;
    final RequestRepository requestRepository;

    @Autowired
    public PrivateUserEventsService(
            EventRepository eventRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            LocationRepository locationRepository,
            RequestRepository requestRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.requestRepository = requestRepository;
    }

    @Transactional
    public EventFullDto saveEvent(Long userId, NewEventDto newEventDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + userId + " не найден"));

        Event event = EventMapper.toEvent(newEventDto);
        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new BadRequestException("Дата события слишком поздняя");
        }
        Location location = locationRepository.save(newEventDto.getLocation());
        event.setCategory(categoryRepository.findById(newEventDto.getCategory())
                .orElseThrow(() -> new NotFoundException("Категория не найдена")));
        event.setLocation(location);
        event.setInitiator(user);
        event.setCreatedOn(LocalDateTime.now());
        event.setState(State.PENDING);

        log.debug(String.valueOf(LogMessages.ADD), "СОБЫТИЕ");
        return EventMapper.toEventFullDto(eventRepository.save(event));
    }


    public List<EventFullDto> getAllEvents(Long userId, int from, int size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + userId + " не найден"));

        log.debug(String.valueOf(LogMessages.GET_ALL), "СОБЫТИЙ");
        Pageable pageable = PageRequest.of(from / size, size);
        return eventRepository.findAllByInitiatorId(userId, pageable)
                .stream()
                .map(EventMapper::toEventFullDto)
                .map(this::setConfirmedRequests)
                .collect(Collectors.toList());
    }

    private EventFullDto setConfirmedRequests(EventFullDto eventDto) {
        eventDto.setConfirmedRequests(requestRepository.countByEventIdAndStatus(eventDto.getId(),
                Status.CONFIRMED));
        return eventDto;
    }

    public EventFullDto getByIdUsersEvens(Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие с id = " + eventId + " не найдено"));

        if (!event.getInitiator().getId().equals(userId)) {
            throw new BadRequestException("Только инициатор может выполнить запрос");
        }

        EventFullDto eventFullDto = EventMapper.toEventFullDto(event);
        eventFullDto.setConfirmedRequests(requestRepository.countByEventIdAndStatus(event.getId(),
                Status.CONFIRMED));
        log.debug(String.valueOf(LogMessages.GET), "СОБЫТИЕ");
        return eventFullDto;
    }

    @Transactional
    public EventFullDto updatePrivateUsersEvents(Long userId, Long eventId,
                                                 UpdateEventUserRequest newEventDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие не найдено"));
        LocalDateTime dateTime = event.getPublishedOn();

        if (event.getState() != null && event.getState().equals(State.PUBLISHED)) {
            throw new ConflictException("Изменить опубликовонное событие нельзя");
        }

        if (newEventDto.getEventDate() != null) {
            LocalDateTime timeEvent = LocalDateTime.parse(newEventDto.getEventDate(), dateTimeFormatter);
            if (timeEvent.isBefore(LocalDateTime.now().plusHours(2)))
                throw new BadRequestException("Дата и время на которые намечено событие не может быть раньше," +
                        " чем через два часа от текущего момента");
            event.setEventDate(timeEvent);
        }

        Optional.ofNullable(newEventDto.getAnnotation()).ifPresent(event::setAnnotation);
        Optional.ofNullable(newEventDto.getDescription()).ifPresent(event::setDescription);
        if (newEventDto.getLocation() != null)
            locationRepository.save(newEventDto.getLocation());
        event.setLocation(newEventDto.getLocation());
        Optional.ofNullable(newEventDto.getPaid()).ifPresent(event::setPaid);
        Optional.ofNullable(newEventDto.getParticipantLimit()).ifPresent(event::setParticipantLimit);
        Optional.ofNullable(newEventDto.getRequestModeration()).ifPresent(event::setRequestModeration);
        if (newEventDto.getStateAction() != null)
            newEventDto.setStateAction(newEventDto.getStateAction().equals("PUBLISH_EVENT") ? "PUBLISHED" : newEventDto.getStateAction());
        if (newEventDto.getStateAction() != null)
            newEventDto.setStateAction(newEventDto.getStateAction().equals("SEND_TO_REVIEW") ? "PENDING" : newEventDto.getStateAction());
        if (newEventDto.getStateAction() != null)
            newEventDto.setStateAction(newEventDto.getStateAction().equals("CANCEL_REVIEW") ? "CANCELED" : newEventDto.getStateAction());
        if (newEventDto.getStateAction() != null)
            Optional.ofNullable(State.valueOf(newEventDto.getStateAction())).ifPresent(event::setState);
        Optional.ofNullable(newEventDto.getTitle()).ifPresent(event::setTitle);

        event.setEventDate(dateTime);
        log.debug(String.valueOf(LogMessages.UPDATE), "СОБЫТИЕ");
        return EventMapper.toEventFullDto(eventRepository.save(event));
    }


    public List<ParticipationRequestDto> getByIdUsersEvensRequests(Long eventId, Long userId) {
        List<ParticipationRequestDto> participationRequestDtosEmpty = new ArrayList<>();
        List<ParticipationRequestDto> participationRequestDtos = requestRepository.findAllByEventIdAndEventInitiatorId(eventId, userId)
                .stream()
                .map(e -> ParticipationRequestMapper.toParticipationRequestDto(e))
                .collect(Collectors.toList());

        log.debug(String.valueOf(LogMessages.GET), "СОБЫТИЕ");
        if (participationRequestDtos.isEmpty())
            return participationRequestDtosEmpty;
        else
            return participationRequestDtos;
    }

    @Transactional
    public EventRequestStatusUpdateResult updateUsersEventsRequests(Long userId, Long eventId,
                                                                    EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {

        Event event = eventRepository.findById(eventId).orElseThrow(() -> {
            throw new NotFoundException("Событие не найдено");
        });

        List<ParticipationRequest> requestsEvent = requestRepository.findAllByEventId(eventId);


        if (!event.getInitiator().getId().equals(userId)) {
            throw new NotFoundException("Вы не владелец события и не имеете право изменять статус заявок");
        }


        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            throw new BadRequestException("Подтверждение не требуется");
        }


        if (event.getParticipantLimit() <= requestRepository
                .countByEventAndStatus(event, Status.CONFIRMED)) {
            throw new ConflictException("Достигнут лимит запросов на участие");
        }


        List<ParticipationRequest> updatedParticipationRequest = new ArrayList<>();
        for (ParticipationRequest request : requestsEvent) {
            if (eventRequestStatusUpdateRequest.getRequestIds().contains(request.getId())) {
                request.setStatus(Status.valueOf(eventRequestStatusUpdateRequest.getStatus()));
                updatedParticipationRequest.add(request);
            }
        }

        requestRepository.saveAll(updatedParticipationRequest);

        EventRequestStatusUpdateResult eventRequestStatusUpdateResult = new EventRequestStatusUpdateResult();

        for (ParticipationRequest iter : updatedParticipationRequest) {
            if (iter.getStatus().equals(Status.CONFIRMED)) {
                eventRequestStatusUpdateResult.getConfirmedRequests().add(ParticipationRequestMapper
                        .toParticipationRequestDto(iter));
            } else if (iter.getStatus().equals(Status.REJECTED)) {
                eventRequestStatusUpdateResult.getRejectedRequests().add(ParticipationRequestMapper
                        .toParticipationRequestDto(iter));
            }
        }

        log.debug(String.valueOf(LogMessages.GET_ALL), "СОБЫТИЙ");
        return eventRequestStatusUpdateResult;
    }

}
