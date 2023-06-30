package ru.practicum.mainmicroservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.messages.ExceptionMessages;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class UserShortDto {
    Long id;

    @NotBlank(message = ExceptionMessages.EMPTY_NAME)
    String name;
}
