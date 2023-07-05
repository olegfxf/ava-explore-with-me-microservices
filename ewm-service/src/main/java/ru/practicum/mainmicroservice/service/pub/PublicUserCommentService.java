package ru.practicum.mainmicroservice.service.pub;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.dto.CommentMapper;
import ru.practicum.mainmicroservice.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicUserCommentService {
    CommentRepository commentRepository;

    public PublicUserCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsForEvent(Long eventId, int from, int size) {

        return commentRepository.findAllByEventId(eventId)
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }
}