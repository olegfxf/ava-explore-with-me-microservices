package ru.practicum.mainmicroservice.controller.priv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.*;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.priv.PrivateUserEventsService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/users/{userId}/events")
public class PrivateUserEventsController {
    private final PrivateUserEventsService privateUserEventsService;

    @Autowired
    public PrivateUserEventsController(PrivateUserEventsService privateUserEventsService) {
        this.privateUserEventsService = privateUserEventsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto saveEvent(@PathVariable Long userId,
                                  @RequestBody @Valid NewEventDto newEventDto) {
        log.debug(String.valueOf(LogMessages.TRY_ADD), "СОБЫТИЯ");
        return privateUserEventsService.saveEvent(userId, newEventDto);
    }


    @GetMapping
    public List<EventFullDto> getAllUsersEvents(@PathVariable Long userId,
                                                @RequestParam(defaultValue = "0") Integer from,
                                                @RequestParam(defaultValue = "10") Integer size) {
        log.debug(String.valueOf(LogMessages.TRY_GET_ALL), "СОБЫТИЙ");
        return privateUserEventsService.getAllEvents(userId, from, size);
    }


    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto getByIdUsersEvens(@PathVariable Long userId,
                                          @PathVariable Long eventId) {
        log.debug(String.valueOf(LogMessages.TRY_GET_OBJECT), "СОБЫТИЕ");
        return privateUserEventsService.getByIdUsersEvens(userId, eventId);
    }


    @PatchMapping("/{eventId}")
    public EventFullDto updatePrivateUsersEvents(@PathVariable Long userId,
                                                 @PathVariable Long eventId,
                                                 @Valid @RequestBody UpdateEventUserRequest eventUserRequestDto) {
        log.debug(String.valueOf(LogMessages.TRY_UPDATE), "СОБЫТИЕ");
        return privateUserEventsService.updatePrivateUsersEvents(userId, eventId, eventUserRequestDto);
    }


    @GetMapping("/{eventId}/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipationRequestDto> getByIdUsersEvensRequests(@PathVariable Long userId,
                                                                   @PathVariable Long eventId) {
        log.debug(String.valueOf(LogMessages.TRY_GET_OBJECT), "СОБЫТИЕ");
        return privateUserEventsService.getByIdUsersEvensRequests(eventId, userId);
    }


    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult updateUsersEventsRequests(@PathVariable Long userId,
                                                                    @PathVariable Long eventId,
                                                                    @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        log.debug(String.valueOf(LogMessages.TRY_UPDATE), "СОБЫТИЯ");
        return privateUserEventsService.updateUsersEventsRequests(userId, eventId, eventRequestStatusUpdateRequest);
    }

}
