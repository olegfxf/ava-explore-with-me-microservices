package ru.practicum.mainmicroservice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.mainmicroservice.event.dto.EventFullDto;
import ru.practicum.mainmicroservice.event.dto.EventMapper;
import ru.practicum.mainmicroservice.event.dto.NewEventDto;
import ru.practicum.mainmicroservice.event.model.Event;
import ru.practicum.mainmicroservice.event.repository.EventRepository;
import ru.practicum.mainmicroservice.user.repository.UserRepository;

@Service
public class PrivateUserService {
    UserRepository userRepository;
    EventRepository eventRepository;

    @Autowired
    public PrivateUserService(UserRepository userRepository,
                              EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public EventFullDto saveEvent(Long id, NewEventDto newEventDto) {
        Event event = EventMapper.toEvent(newEventDto);
        event.setId(id);
        EventFullDto eventFullDto = EventMapper.toEventFullDto(eventRepository.save(event));
        ////eventFullDto.setId(id);
        //EventMapper -> необходиммо добавить в EventFull Dto:
        //                .confirmedRequests(event.getC)
        //                .requestModeration(event.getR)

        return eventFullDto;
    }


}
