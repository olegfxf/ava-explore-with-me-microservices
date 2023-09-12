package ru.practicum.mainmicroservice.dto.category;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainmicroservice.messages.ExceptionMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CategoryDto {
    @NonNull
    Long id;

    @NotBlank
    @Size(min = 1, max = 50, message = ExceptionMessages.SIZE_NAME_CATEGORY)
    String name;

}
