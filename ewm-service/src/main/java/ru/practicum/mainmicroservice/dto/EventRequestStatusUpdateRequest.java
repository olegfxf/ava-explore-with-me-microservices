package ru.practicum.mainmicroservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class EventRequestStatusUpdateRequest {

    List<Long> requestIds;
    String status;
}
