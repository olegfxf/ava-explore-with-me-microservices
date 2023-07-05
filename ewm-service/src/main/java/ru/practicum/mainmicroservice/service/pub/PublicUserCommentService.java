package ru.practicum.mainmicroservice.service.pub;

import org.springframework.stereotype.Service;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.dto.CommentMapper;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.model.Comment;
import ru.practicum.mainmicroservice.model.Event;
import ru.practicum.mainmicroservice.model.User;
import ru.practicum.mainmicroservice.repository.CommentRepository;
import ru.practicum.mainmicroservice.repository.EventRepository;
import ru.practicum.mainmicroservice.repository.UserRepository;

@Service
public class PublicUserCommentService {
    CommentRepository commentRepository;
    UserRepository userRepository;
    EventRepository eventRepository;

    public PublicUserCommentService(CommentRepository commentRepository,
                                    UserRepository userRepository,
                                    EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public CommentDto saveComment(Long userId, Long eventId, CommentDto commentDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(" Пользователь не существует"));
        Event event = eventRepository.findById(eventId).orElseThrow(()->
                new NotFoundException("Событие не существует"));
        Comment comment = CommentMapper.toComment(commentDto);
        comment.setUser(user);
        comment.setEvent(event);
        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }
}
