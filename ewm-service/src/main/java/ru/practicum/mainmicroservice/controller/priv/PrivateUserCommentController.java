package ru.practicum.mainmicroservice.controller.priv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.CommentDto;
import ru.practicum.mainmicroservice.service.pub.PublicUserCommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;


@Slf4j
@RestController
@RequestMapping("/users/{userId}/comments")
public class PrivateUserCommentController {

    final PublicUserCommentService publicUserCommentService;

    public PrivateUserCommentController(PublicUserCommentService publicUserCommentService) {
        this.publicUserCommentService = publicUserCommentService;
    }

    @PostMapping("/{eventId}")
    public CommentDto createComment(@Positive @PathVariable Long userId,
                                    @Positive @PathVariable Long eventId,
                                    @Valid @RequestBody CommentDto commentDto) {
        log.info("create comment by user {}", userId);
        return publicUserCommentService.saveComment(userId, eventId, commentDto);
    }




}
