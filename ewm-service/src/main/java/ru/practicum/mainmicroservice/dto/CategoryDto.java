package ru.practicum.mainmicroservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @Size(min = 1, max = 50, message = "Длина имени категории должна быть больше 1 и меньше 50")
    String name;

}
