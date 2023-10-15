package ru.practicum.mainmicroservice.dto.location;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    float lat;

    float lon;
}
