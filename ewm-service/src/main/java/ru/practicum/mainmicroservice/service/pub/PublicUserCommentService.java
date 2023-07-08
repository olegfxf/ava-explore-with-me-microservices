package ru.practicum.mainmicroservice.service.pub;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.dto.CommentMapper;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.Event;
import ru.practicum.mainmicroservice.repository.CommentRepository;
import ru.practicum.mainmicroservice.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class PublicUserCommentService {
    final CommentRepository commentRepository;
    final EventRepository eventRepository;

    public PublicUserCommentService(CommentRepository commentRepository,
                                    EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
    }


    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsForEvent(Long eventId, int from, int size) {
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException("Событие не найдено"));
        Pageable pageable = GetPagable.of(from, size);

        log.debug(String.valueOf(LogMessages.GET_ALL), "КОММЕНТАРИЕВ ПО СОБЫТИЮ");
        return commentRepository.findAllByEvent(event, pageable)
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }


}