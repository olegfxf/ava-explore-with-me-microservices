package ru.practicum.mainmicroservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.category.CategoryDto;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.PublicCategoryService;

import java.util.List;

@Slf4j
@RestController
public class PublicCategoryController {

    private final PublicCategoryService publicCategoryService;

    public PublicCategoryController(PublicCategoryService publicCategoryService) {
        this.publicCategoryService = publicCategoryService;
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getAllPublicCategories(@RequestParam(defaultValue = "0") Integer from,
                                                    @RequestParam(defaultValue = "10") Integer size) {
        log.debug(String.valueOf(LogMessages.GET_ALL), "Категорий");
        return publicCategoryService.getAllPublicCategories(from, size);
    }

    @GetMapping("/categories/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getAllPublicCategoriesById(@PathVariable Long catId) {
        log.debug(String.valueOf(LogMessages.GET_ALL), "Категорий");
        return publicCategoryService.getAllPublicCategoriesById(catId);
    }

}
