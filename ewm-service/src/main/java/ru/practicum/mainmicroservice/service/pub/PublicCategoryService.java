package ru.practicum.mainmicroservice.service.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainmicroservice.dto.CategoryDto;
import ru.practicum.mainmicroservice.dto.CategoryMapper;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PublicCategoryService {
    private CategoryRepository categoryRepository;

    public PublicCategoryService(
            CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getAllPublicCategories(Integer from, Integer size) {
        Pageable pageable = (Pageable) PageRequest.of(from / size, size);
        log.debug(String.valueOf(LogMessages.GET_ALL), "Категорий");
        return categoryRepository.findAll(pageable).stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto getAllPublicCategoriesById(Long catId) {
        log.debug(String.valueOf(LogMessages.GET_ALL), "Категорий");
        return CategoryMapper.toCategoryDto(categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Категория не найдена")));
    }

}
