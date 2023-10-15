package ru.practicum.mainmicroservice.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.messages.ExceptionMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDto {
    Long id;

    @NotNull
    @NotBlank
    @NotEmpty(message = ExceptionMessages.EMPTY_NAME)
    @Size(min = 2)
    @Size(max = 250)
    String name;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 6)
    @Size(max = 254)
    String email;


}
