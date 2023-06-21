package ru.practicum.mainmicroservice.category.dto;

import ru.practicum.mainmicroservice.category.model.Category;

public class CategoryMapper {
    public static CategoryDto toCategoryDtoReq(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .build();
    }

    public static Category toCategory(NewCategoryDto newCategoryDto) {
        return Category.builder()
                .name(newCategoryDto.getName())
                .build();
    }

    public static CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toCategory(CategoryNewEventDto categoryNewEventDto) {
        return Category.builder()
                .id(categoryNewEventDto.getId())
                .build();
    }
}
