package ru.practicum.mainmicroservice.category.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CategoryNewEventDto {
    @NonNull
    Long id;

}
