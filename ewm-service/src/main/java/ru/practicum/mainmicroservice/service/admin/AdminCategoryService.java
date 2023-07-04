package ru.practicum.mainmicroservice.service.admin;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainmicroservice.dto.CategoryDto;
import ru.practicum.mainmicroservice.dto.CategoryMapper;
import ru.practicum.mainmicroservice.dto.NewCategoryDto;
import ru.practicum.mainmicroservice.exception.ConflictException;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.Category;
import ru.practicum.mainmicroservice.repository.CategoryRepository;
import ru.practicum.mainmicroservice.repository.EventRepository;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminCategoryService {
    final CategoryRepository categoryRepository;
    final EventRepository eventRepository;

    //@Autowired
    public AdminCategoryService(CategoryRepository categoryRepository,
                                EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public CategoryDto saveCategory(NewCategoryDto newCategoryDto) {
        Category category = categoryRepository.save(CategoryMapper.toCategory(newCategoryDto));
        log.debug(String.valueOf(LogMessages.ADD), "КАТЕГОРИЯ");
        return CategoryMapper.toCategoryDto(category);
    }

    @Transactional
    public Category deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Категория с id = " + id + " не найдена"));
        if (eventRepository.findFirstByCategoryId(id) != null)
            throw new ConflictException("Существуют события, связанные с категорией");

        log.debug(String.valueOf(LogMessages.REMOVE), "КАТЕГОРИЯ");
        categoryRepository.deleteById(id);
        return category;
    }

    @Transactional
    public CategoryDto updateCategory(Long id, NewCategoryDto newCategoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id=" + id + " was not found"));
        log.debug(String.valueOf(LogMessages.PATCH), "КАТЕГОРИЯ");
        if (newCategoryDto.getName() != null)
            category.setName(newCategoryDto.getName());
        Category category1 = categoryRepository.save(category);
        log.debug(String.valueOf(LogMessages.UPDATE), "КАТЕГОРИЯ");
        return CategoryMapper.toCategoryDto(category1);

    }
}
