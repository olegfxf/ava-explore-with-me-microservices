package ru.practicum.mainmicroservice.category.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.category.dto.CategoryDto;
import ru.practicum.mainmicroservice.category.dto.NewCategoryDto;
import ru.practicum.mainmicroservice.category.service.AdminCategoryService;
import ru.practicum.mainmicroservice.messages.LogMessages;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    AdminCategoryService adminCategoryService;

    @Autowired
    public AdminCategoryController(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

    @PostMapping
    public CategoryDto saveCategory(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.debug(String.valueOf(LogMessages.TRY_ADD), "категории");
        return adminCategoryService.saveCategory(newCategoryDto);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id) {
        log.debug(String.valueOf(LogMessages.TRY_REMOVE_OBJECT), "категория");
        adminCategoryService.deleteCategory(id);
    }

    @PatchMapping("{id}")
    public CategoryDto updateCategory(@PathVariable Long id) {
        log.debug(String.valueOf(LogMessages.TRY_UPDATE), "категория");
        return adminCategoryService.updateCategory(id);
    }

}
