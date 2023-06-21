package ru.practicum.mainmicroservice.event.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.mainmicroservice.category.dto.CategoryDto;
import ru.practicum.mainmicroservice.category.dto.CategoryMapper;
import ru.practicum.mainmicroservice.category.model.Category;
import ru.practicum.mainmicroservice.category.repository.CategoryRepository;
import ru.practicum.mainmicroservice.event.model.Event;
import ru.practicum.mainmicroservice.user.dto.UserMapper;

@Component
public class EventMapper {
    static CategoryRepository categoryRepository;

    @Autowired
    public EventMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static Event toEvent(NewEventDto newEventDto) {
        Category category = categoryRepository.findById(newEventDto.getCategory()).get();
        return Event.builder()
                .annotation(newEventDto.getAnnotation())
                .category(category)
                .description(newEventDto.getDescription())
                .eventDate(newEventDto.getEventDate())
                .location(newEventDto.getLocation())
                .paid(newEventDto.isPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .requestModeration(newEventDto.isRequestModeration())
                .title(newEventDto.getTitle())
                .build();
    }

    public static EventFullDto toEventFullDto(Event event){
        return EventFullDto.builder()
                .id(event.getId())
                .category(CategoryDto.builder().build())
//                .confirmedRequests(event.getC)
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .location(event.getLocation())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
//                .requestModeration(event.getR)
                .state(event.getState())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }
}
