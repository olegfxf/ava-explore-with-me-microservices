package ru.practicum.mainmicroservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.PrivateUserRequestService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users/{userId}/requests")
public class PrivateUserRequestController {
    private final PrivateUserRequestService privateUserRequestService;

    public PrivateUserRequestController(PrivateUserRequestService privateUserRequestService) {
        this.privateUserRequestService = privateUserRequestService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipationRequestDto> getAllUserRequest(@PathVariable Long userId) {

        log.debug(String.valueOf(LogMessages.TRY_GET_ALL), "ЗАЯВКИ");
        return privateUserRequestService.getAllUserRequest(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto saveUserRequest(@PathVariable Long userId,
                                                   @RequestParam Long eventId) {
        log.debug(String.valueOf(LogMessages.TRY_ADD), "ЗАЯВКУ");
        return privateUserRequestService.saveUserRequest(userId, eventId);
    }


    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto updateUserRequestCancel(@PathVariable Long userId,
                                                           @PathVariable Long requestId) {
        log.debug(String.valueOf(LogMessages.TRY_UPDATE), "ОТМЕНА ЗАПРОСА");
        return privateUserRequestService.updateUserRequestCancel(userId, requestId);
    }


}
