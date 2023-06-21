package ru.practicum.mainmicroservice.user.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.messages.ExceptionMessages;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDto {
    Long id;

    @NotNull
    @NotBlank(message = ExceptionMessages.EMPTY_NAME)
    String name;

    @NotNull
    String email;


}
