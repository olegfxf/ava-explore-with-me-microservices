package ru.practicum.mainmicroservice.dto.category;

import lombok.*;
import ru.practicum.mainmicroservice.messages.ExceptionMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class NewCategoryDto {
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 50, message = ExceptionMessages.SIZE_NAME_CATEGORY)
    private String name;
}
