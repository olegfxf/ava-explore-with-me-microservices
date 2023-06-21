package ru.practicum.mainmicroservice.event.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.event.model.Location;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class NewEventDto {

    @NonNull
    String annotation;

    Long category;

    String description;

    LocalDateTime eventDate;


    Location location;

    boolean paid;

    Integer participantLimit;


    boolean requestModeration;


    String title;


}
