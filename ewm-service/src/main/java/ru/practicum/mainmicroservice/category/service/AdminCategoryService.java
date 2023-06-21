package ru.practicum.mainmicroservice.category.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainmicroservice.category.dto.CategoryDto;
import ru.practicum.mainmicroservice.category.dto.CategoryMapper;
import ru.practicum.mainmicroservice.category.dto.NewCategoryDto;
import ru.practicum.mainmicroservice.category.model.Category;
import ru.practicum.mainmicroservice.category.repository.CategoryRepository;
import ru.practicum.mainmicroservice.event.repository.EventRepository;
import ru.practicum.mainmicroservice.exception.BadRequestException;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;

@Slf4j
@Service
public class AdminCategoryService {
    CategoryRepository categoryRepository;
    EventRepository eventRepository;

    @Autowired
    public AdminCategoryService(CategoryRepository categoryRepository,
                                EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    public CategoryDto saveCategory(NewCategoryDto newCategoryDto) {
        //System.out.println(" QQQ2 " + CategoryMapper.toCategory(newCategoryDto));
        log.debug(String.valueOf(LogMessages.ADD), "категория");
        return CategoryMapper.toCategoryDtoReq(categoryRepository.save(CategoryMapper.toCategory(newCategoryDto)));
    }

    public void deleteCategory(Long id) {
        if (eventRepository.findFirstByCategoryId(id) == null)
            throw new BadRequestException("Существуют события, связанные с категорией");
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Категория с id = " + id + " не найдена"));
        log.debug(String.valueOf(LogMessages.REMOVE), id);
        categoryRepository.delete(category);
    }

    public CategoryDto updateCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id=" + id + " was not found"));
        log.debug(String.valueOf(LogMessages.PATCH), "Категория");
        return CategoryMapper.toCategoryDto(categoryRepository.save(category));

    }
}
