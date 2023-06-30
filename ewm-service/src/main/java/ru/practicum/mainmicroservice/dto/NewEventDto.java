package ru.practicum.mainmicroservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {

    @NotBlank
    @NonNull
    @NotEmpty
    @Size(min = 20, max = 2000)
    String annotation;

    Long category;

    @NotBlank
    @NonNull
    @NotEmpty
    @Size(min = 20, max = 7000)
    String description;

    @NotBlank
    String eventDate;

    Location location;

    Boolean paid = false;

    Integer participantLimit = 0;

    Boolean requestModeration = true;

    String stateAction;

    @NotBlank
    @Size(min = 3, max = 120)
    String title;


}
