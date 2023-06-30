package ru.practicum.mainmicroservice.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.CategoryDto;
import ru.practicum.mainmicroservice.dto.NewCategoryDto;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.Category;
import ru.practicum.mainmicroservice.service.admin.AdminCategoryService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
@Validated
public class AdminCategoryController {
    private final AdminCategoryService adminCategoryService;

    @Autowired
    public AdminCategoryController(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto saveCategory(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.debug(String.valueOf(LogMessages.TRY_ADD), "КАТЕГОРИЯ");
        System.out.println(newCategoryDto.getName().length() + " length");
        return adminCategoryService.saveCategory(newCategoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Category deleteCategory(@PathVariable Long id) {
        log.debug(String.valueOf(LogMessages.TRY_REMOVE_OBJECT), "КАТЕГОРИЯ");
        return adminCategoryService.deleteCategory(id);
    }

    @PatchMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id,
                                      @Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.debug(String.valueOf(LogMessages.TRY_UPDATE), "КАТЕГОРИЯ");
        return adminCategoryService.updateCategory(id, newCategoryDto);
    }

}
