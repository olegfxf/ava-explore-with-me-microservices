package ru.practicum.mainmicroservice.service.priv;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.dto.CommentMapper;
import ru.practicum.mainmicroservice.exception.BadRequestException;
import ru.practicum.mainmicroservice.exception.NotFoundException;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.model.Comment;
import ru.practicum.mainmicroservice.model.Event;
import ru.practicum.mainmicroservice.model.User;
import ru.practicum.mainmicroservice.repository.CommentRepository;
import ru.practicum.mainmicroservice.repository.EventRepository;
import ru.practicum.mainmicroservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class PrivateUserCommentService {
    final CommentRepository commentRepository;
    final UserRepository userRepository;
    final EventRepository eventRepository;


    public CommentDto saveComment(Long userId, Long eventId, CommentDto commentDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(" Пользователь не существует"));
        Event event = eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException("Событие не существует"));
        Comment comment = CommentMapper.toComment(commentDto);
        comment.setUser(user);
        comment.setEvent(event);

        log.debug(String.valueOf(LogMessages.ADD), "КОММЕНТАРИЙ");
        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }


    public List<CommentDto> getAllCommentsByUser(Long userId, int from, int size) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(" Пользователь не существует"));

        log.debug(String.valueOf(LogMessages.GET_ALL), "КОММЕНТАРИИ ПОЛЬЗОВАТЕЛЯ");
        return commentRepository.findAllByUser(user, PageRequest.of(from / size, size))
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }


    public CommentDto updateComment(Long commentId, Long userId, CommentDto commentDto) {
        Comment comment = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(() -> new BadRequestException("Только автор может изменинть комментарий"));
        comment.setText(commentDto.getText());

        log.debug(String.valueOf(LogMessages.UPDATE), "КОММЕНТАРИИ");
        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }


    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(() -> new BadRequestException("Только автор может удалить комментарий"));
        log.debug(String.valueOf(LogMessages.REMOVE), "КОММЕНТАРИИ");
        commentRepository.delete(comment);
    }
}
