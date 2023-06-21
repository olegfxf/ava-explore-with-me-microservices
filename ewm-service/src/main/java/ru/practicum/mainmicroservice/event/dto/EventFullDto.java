package ru.practicum.mainmicroservice.event.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.category.dto.CategoryDto;
import ru.practicum.mainmicroservice.event.model.Location;
import ru.practicum.mainmicroservice.event.model.State;
import ru.practicum.mainmicroservice.user.dto.UserShortDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EventFullDto {
    Long id;

    @NotNull
    String annotation;

    @NotNull
    CategoryDto category;

    Integer confirmedRequests;

    LocalDateTime createdOn;

    String description;

    @NotNull
    LocalDateTime eventDate;

    @NotNull
    UserShortDto initiator;

    @NotNull
    Location location;

    @NotNull
    boolean paid;

    Integer participantLimit;

    LocalDateTime publishedOn;

    boolean requestModeration;

    State state;

    @NotNull
    String title;

    Integer views;
}
