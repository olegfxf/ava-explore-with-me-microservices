package ru.practicum.mainmicroservice.event.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.category.dto.CategoryDto;
import ru.practicum.mainmicroservice.user.dto.UserShortDto;

import java.time.LocalDateTime;
@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EventShortDto {

    Long id;

    String annotation;

    CategoryDto category;

    Integer confirmedRequests;

    LocalDateTime eventDate;
    //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")

    UserShortDto initiator;

    boolean paid;

    String title;

    Integer views;
}
