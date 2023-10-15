package ru.practicum.mainmicroservice.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.dto.user.UserShortDto;
import ru.practicum.mainmicroservice.dto.category.CategoryDto;

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

    Integer confirmedRequests = 10;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    UserShortDto initiator;

    Boolean paid;

    String title;

    Long views;
}
