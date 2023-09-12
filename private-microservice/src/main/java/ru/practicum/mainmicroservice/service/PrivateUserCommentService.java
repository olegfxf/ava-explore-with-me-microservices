package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.comment.CommentDto;

import java.util.List;

public interface PrivateUserCommentService {
    CommentDto saveComment(Long userId, Long eventId, CommentDto commentDto);

    List<CommentDto> getAllCommentsByUser(Long userId, int from, int size);

    CommentDto updateComment(Long commentId, Long userId, CommentDto commentDto);

    void deleteComment(Long commentId, Long userId);
}
