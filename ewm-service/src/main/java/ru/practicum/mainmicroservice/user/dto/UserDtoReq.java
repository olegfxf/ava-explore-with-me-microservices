package ru.practicum.mainmicroservice.user.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.messages.ExceptionMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDtoReq {

    @NotNull
    @NotBlank(message = ExceptionMessages.EMPTY_NAME)
    String name;

    @NotNull
    String email;


}
