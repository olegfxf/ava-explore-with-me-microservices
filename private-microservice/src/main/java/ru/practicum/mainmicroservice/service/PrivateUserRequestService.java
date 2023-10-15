package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.request.ParticipationRequestDto;

import java.util.List;

public interface PrivateUserRequestService {
    List<ParticipationRequestDto> getAllUserRequest(Long userId);

    ParticipationRequestDto saveUserRequest(Long userId, Long eventId);

    ParticipationRequestDto updateUserRequestCancel(Long userId, Long requestId);

}
