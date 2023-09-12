package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.category.CategoryDto;
import ru.practicum.mainmicroservice.dto.category.NewCategoryDto;

import javax.validation.Valid;

public interface AdminCategoryService {
    CategoryDto saveCategory(@Valid NewCategoryDto newCategoryDto);

    void deleteCategory(Long id);

    CategoryDto updateCategory(Long id, NewCategoryDto newCategoryDto);

}
