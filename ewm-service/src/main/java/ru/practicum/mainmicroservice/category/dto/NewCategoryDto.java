package ru.practicum.mainmicroservice.category.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class NewCategoryDto {
    @NotBlank
    @Size(min = 1, max = 50, message = "Длина имени категории должна быть больше 1 и меньше 50")
    String name;
}
