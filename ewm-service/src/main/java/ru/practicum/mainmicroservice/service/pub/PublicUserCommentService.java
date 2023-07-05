package ru.practicum.mainmicroservice.service.pub;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.dto.CommentMapper;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.model.Event;
import ru.practicum.mainmicroservice.repository.CommentRepository;
import ru.practicum.mainmicroservice.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicUserCommentService {
    CommentRepository commentRepository;
    EventRepository eventRepository;

    public PublicUserCommentService(CommentRepository commentRepository,
                                    EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
    }


    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsForEvent(Long eventId, int from, int size) {
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException("Событие не найдено"));
        PageRequest pageRequest = PageRequest.of(from / size, size);
        return commentRepository.findAllByEvent(event, pageRequest)
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }


}