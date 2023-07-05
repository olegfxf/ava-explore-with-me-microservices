package ru.practicum.mainmicroservice.dto;

import lombok.*;

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
    @Size(max = 50, message = "Длина имени категории должна быть меньше 50")
    private String name;
}
