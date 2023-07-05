package ru.practicum.mainmicroservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class NewCompilationDto {
    List<Long> events;

    Boolean pinned = false;

    @Size(min = 1)
    @Size(max = 50)
    @NonNull
    @NotBlank
    String title;
}
