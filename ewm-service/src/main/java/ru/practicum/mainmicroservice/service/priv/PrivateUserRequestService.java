package ru.practicum.mainmicroservice.service.priv;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainmicroservice.dto.ParticipationRequestDto;
import ru.practicum.mainmicroservice.dto.ParticipationRequestMapper;
import ru.practicum.mainmicroservice.exception.BadRequestException;
import ru.practicum.mainmicroservice.exception.ConflictException;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.*;
import ru.practicum.mainmicroservice.repository.EventRepository;
import ru.practicum.mainmicroservice.repository.RequestRepository;
import ru.practicum.mainmicroservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrivateUserRequestService {
    final RequestRepository requestRepository;
    UserRepository userRepository;
    final EventRepository eventRepository;

    //@Autowired
    public PrivateUserRequestService(RequestRepository requestRepository,
                                     UserRepository userRepository,
                                     EventRepository eventRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    public List<ParticipationRequestDto> getAllUserRequest(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не существует с id " + userId));
        log.debug(String.valueOf(LogMessages.GET_ALL), "Заявок");
        return requestRepository.findAllByRequester(user).stream()
                .map(ParticipationRequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ParticipationRequestDto saveUserRequest(Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие не найдено"));

        if (requestRepository.existsByRequesterIdAndEventId(userId, eventId))
            throw new IllegalStateException("Запрос на участие уже существует");

        if (userId.equals(event.getInitiator().getId()))
            throw new IllegalStateException("Инициатор события не может добавить запрос на участие в своём событии");


        if (!eventRepository.existsByIdAndState(eventId, State.PUBLISHED))
            throw new IllegalStateException("Нельзя участвовать в неопубликованном событии");


        if (event.getParticipantLimit() != 0 && event.getParticipantLimit() <= requestRepository
                .countByEventAndStatus(event, Status.CONFIRMED)) {
            throw new ConflictException("Достигнут лимит запросов на участие");
        }


        ParticipationRequest participation = ParticipationRequest
                .builder()
                .created(LocalDateTime.now())
                .requester(user)
                .event(event)
                .status((event.getParticipantLimit() == 0) || (!event.getRequestModeration()) ? Status.CONFIRMED : Status.PENDING)
                .build();


        log.debug(String.valueOf(LogMessages.ADD), "ЗАЯВКЕ");
        return ParticipationRequestMapper.toParticipationRequestDto(requestRepository.save(participation));

    }

    @Transactional
    public ParticipationRequestDto updateUserRequestCancel(Long userId, Long requestId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));


        ParticipationRequest participationRequest = requestRepository.findByIdAndRequester(requestId, user);
        if (participationRequest == null)
            new BadRequestException("Запрос не найден или недоступен для текущего пользователя");

        participationRequest.setStatus(Status.CANCELED);
        return ParticipationRequestMapper.toParticipationRequestDto(requestRepository.save(participationRequest));
    }
}
