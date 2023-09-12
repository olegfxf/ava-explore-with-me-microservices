package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.category.CategoryDto;

import java.util.List;

public interface PublicCategoryService {
    List<CategoryDto> getAllPublicCategories(Integer from, Integer size);

    CategoryDto getAllPublicCategoriesById(Long catId);
}
