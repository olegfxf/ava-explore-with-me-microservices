package ru.practicum.mainmicroservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.model.Status;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationRequestDto {
    Long id;

    String created;

    Long event;

    Long requester;

    Status status;
}
