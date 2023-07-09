package ru.practicum.mainmicroservice.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;

    @NotEmpty
    @Size(max = 2000)
    private String text;

    private String authorName;

    private String created;
}
