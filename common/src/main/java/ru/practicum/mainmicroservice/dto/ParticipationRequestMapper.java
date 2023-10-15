package ru.practicum.mainmicroservice.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.mainmicroservice.dto.request.ParticipationRequestDto;
import ru.practicum.mainmicroservice.model.ParticipationRequest;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class ParticipationRequestMapper {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public ParticipationRequestDto toParticipationRequestDto(ParticipationRequest participationRequest) {
        return ParticipationRequestDto.builder()
                .id(participationRequest.getId())
                .created(participationRequest.getCreated().format(dateTimeFormatter))
                .event(participationRequest.getEvent().getId())
                .requester(participationRequest.getRequester().getId())
                .status(participationRequest.getStatus())
                .build();
    }
}
