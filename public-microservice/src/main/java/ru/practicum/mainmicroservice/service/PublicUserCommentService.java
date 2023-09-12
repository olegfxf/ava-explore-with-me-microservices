package ru.practicum.mainmicroservice.service;

import ru.practicum.mainmicroservice.dto.comment.CommentDto;

import java.util.List;

public interface PublicUserCommentService {
    List<CommentDto> getAllCommentsForEvent(Long eventId, int from, int size);
}
