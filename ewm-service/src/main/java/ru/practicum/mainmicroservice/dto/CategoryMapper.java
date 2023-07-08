package ru.practicum.mainmicroservice.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.mainmicroservice.model.Category;

@UtilityClass
public class CategoryMapper {

    public Category toCategory(NewCategoryDto newCategoryDto) {
        return Category.builder()
                .name(newCategoryDto.getName())
                .build();
    }

    public CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }


    public Category toCategory(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }


}
